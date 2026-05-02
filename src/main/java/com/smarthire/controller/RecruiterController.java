package com.smarthire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smarthire.dto.JobRequest;
import com.smarthire.service.RecruiterService;
import com.smarthire.service.RankingService;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private RankingService rankingService;

	@GetMapping("/dashboard")
	public String recruiterDashboard() {
		return "Welcome Recruiter - Access Granted";
	}

	@GetMapping("/rank-candidate")
	public String rankCandidate(@RequestParam("jobId") Long jobId,
			@RequestParam("candidateEmail") String candidateEmail) {

		return rankingService.calculateMatch(jobId, candidateEmail);
	}

	@GetMapping("/rank-all-candidates")
	public String rankAllCandidates(@RequestParam("jobId") Long jobId) {

		return rankingService.rankAllCandidates(jobId);
	}

	@PostMapping("/post-job")
	public String postJob(@RequestBody JobRequest jobRequest, @RequestParam("email") String recruiterEmail) {

		return recruiterService.postJob(jobRequest, recruiterEmail);
	}
}