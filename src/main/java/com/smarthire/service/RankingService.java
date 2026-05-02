package com.smarthire.service;

public interface RankingService {

	String calculateMatch(Long jobId, String candidateEmail);

	String rankAllCandidates(Long jobId);
}