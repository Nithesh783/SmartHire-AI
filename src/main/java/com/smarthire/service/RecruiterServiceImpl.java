package com.smarthire.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthire.dto.JobRequest;
import com.smarthire.entity.Job;
import com.smarthire.entity.Role;
import com.smarthire.entity.User;
import com.smarthire.repository.JobRepository;
import com.smarthire.repository.UserRepository;

@Service
public class RecruiterServiceImpl implements RecruiterService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String postJob(JobRequest jobRequest, String recruiterEmail) {

		Optional<User> optionalRecruiter = userRepository.findByEmail(recruiterEmail);

		if (optionalRecruiter.isEmpty()) {
			return "Recruiter not found";
		}

		User recruiter = optionalRecruiter.get();

		if (recruiter.getRole() != Role.RECRUITER) {
			return "Access denied: Only recruiters can post jobs";
		}

		Job job = new Job();

		job.setTitle(jobRequest.getTitle());
		job.setDescription(jobRequest.getDescription());
		job.setRequiredSkills(jobRequest.getRequiredSkills());
		job.setExperienceLevel(jobRequest.getExperienceLevel());
		job.setCreatedAt(LocalDateTime.now());
		job.setRecruiter(recruiter);

		jobRepository.save(job);

		return "Job posted successfully";
	}
}