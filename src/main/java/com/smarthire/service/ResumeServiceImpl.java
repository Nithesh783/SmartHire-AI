package com.smarthire.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smarthire.dto.ResumeResponse;
import com.smarthire.entity.Resume;
import com.smarthire.entity.User;
import com.smarthire.repository.ResumeRepository;
import com.smarthire.repository.UserRepository;
import com.smarthire.parser.ResumeParser;
import com.smarthire.parser.SkillExtractor;

@Service
public class ResumeServiceImpl implements ResumeService {

	private static final String UPLOAD_DIR = "/Users/nitheshkumar/SmartHireUploads/resumes/";

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResumeParser resumeParser;

	@Autowired
	private SkillExtractor skillExtractor;

	@Override
	public ResumeResponse uploadResume(MultipartFile file, String email) {

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			return new ResumeResponse("User not found", null, false, null);
		}

		User user = optionalUser.get();

		try {

			Optional<Resume> existingResume = resumeRepository.findByUser(user);

			if (existingResume.isPresent()) {
				resumeRepository.delete(existingResume.get());
			}

			File directory = new File(UPLOAD_DIR);

			if (!directory.exists()) {
				directory.mkdirs();
			}

			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			String filePath = UPLOAD_DIR + fileName;

			File destinationFile = new File(filePath);

			file.transferTo(destinationFile);

			String resumeText = resumeParser.parseResume(filePath);

			String extractedSkills = skillExtractor.extractSkills(resumeText);

			Resume resume = new Resume();
			resume.setFileName(fileName);
			resume.setFilePath(filePath);
			resume.setUploadedAt(LocalDateTime.now());
			resume.setSkills(extractedSkills);
			resume.setUser(user);

			resumeRepository.save(resume);

			return new ResumeResponse("Resume uploaded successfully", fileName, true, extractedSkills);

		} catch (IOException e) {

			return new ResumeResponse("Resume upload failed: " + e.getMessage(), null, false, null);
		}
	}
}