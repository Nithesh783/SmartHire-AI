package com.smarthire.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthire.entity.Job;
import com.smarthire.entity.Resume;
import com.smarthire.entity.User;
import com.smarthire.repository.JobRepository;
import com.smarthire.repository.ResumeRepository;
import com.smarthire.repository.UserRepository;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Override
	public String calculateMatch(Long jobId, String candidateEmail) {

		Optional<Job> optionalJob = jobRepository.findById(jobId);

		if (optionalJob.isEmpty()) {
			return "Job not found";
		}

		Optional<User> optionalUser = userRepository.findByEmail(candidateEmail);

		if (optionalUser.isEmpty()) {
			return "Candidate not found";
		}

		Optional<Resume> optionalResume = resumeRepository.findByUser(optionalUser.get());

		if (optionalResume.isEmpty()) {
			return "Resume not found for candidate";
		}

		Job job = optionalJob.get();
		Resume resume = optionalResume.get();

		List<String> jobSkills = Arrays.stream(job.getRequiredSkills().split(",")).map(this::normalizeSkill).distinct()
				.collect(Collectors.toList());

		List<String> candidateSkills = Arrays.stream(resume.getSkills().split(",")).map(this::normalizeSkill).distinct()
				.collect(Collectors.toList());

		long matchedSkills = jobSkills.stream().filter(candidateSkills::contains).count();

		double matchPercentage = ((double) matchedSkills / jobSkills.size()) * 100;

		return "Candidate Match Score: " + matchPercentage + "% (" + matchedSkills + "/" + jobSkills.size()
				+ " skills matched)";
	}

	@Override
	public String rankAllCandidates(Long jobId) {

		Optional<Job> optionalJob = jobRepository.findById(jobId);

		if (optionalJob.isEmpty()) {
			return "Job not found";
		}

		Job job = optionalJob.get();

		List<String> jobSkills = Arrays.stream(job.getRequiredSkills().split(",")).map(this::normalizeSkill).distinct()
				.collect(Collectors.toList());

		List<Resume> resumes = resumeRepository.findAll();

		if (resumes.isEmpty()) {
			return "No candidates found";
		}

		List<String> rankings = resumes.stream().map(resume -> {

			List<String> candidateSkills = Arrays.stream(resume.getSkills().split(",")).map(this::normalizeSkill)
					.distinct().collect(Collectors.toList());

			long matchedSkills = jobSkills.stream().filter(candidateSkills::contains).count();

			double matchPercentage = ((double) matchedSkills / jobSkills.size()) * 100;

			return resume.getUser().getName() + " - " + matchPercentage + "%";
		}).sorted((a, b) -> {
			double scoreA = Double.parseDouble(a.split("-")[1].replace("%", "").trim());
			double scoreB = Double.parseDouble(b.split("-")[1].replace("%", "").trim());
			return Double.compare(scoreB, scoreA);
		}).collect(Collectors.toList());

		return "Candidate Rankings for Job ID " + jobId + ":\n" + String.join("\n", rankings);
	}

	private String normalizeSkill(String skill) {

		skill = skill.trim().toLowerCase();

		if (skill.contains("spring"))
			return "spring";
		if (skill.contains("react"))
			return "react";
		if (skill.equals("js"))
			return "javascript";
		if (skill.contains("javascript"))
			return "javascript";
		if (skill.contains("postgres"))
			return "postgresql";
		if (skill.contains("html"))
			return "html";
		if (skill.contains("css"))
			return "css";

		return skill;
	}
}