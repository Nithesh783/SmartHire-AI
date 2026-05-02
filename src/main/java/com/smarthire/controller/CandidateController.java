package com.smarthire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.smarthire.dto.ResumeResponse;
import com.smarthire.service.ResumeService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private ResumeService resumeService;

	@GetMapping("/profile")
	public String candidateProfile() {
		return "Welcome Candidate - Access Granted";
	}

	@PostMapping("/upload-resume")
	public ResumeResponse uploadResume(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {

		return resumeService.uploadResume(file, email);
	}
}