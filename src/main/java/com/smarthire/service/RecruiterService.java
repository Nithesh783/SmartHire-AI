package com.smarthire.service;

import com.smarthire.dto.JobRequest;

public interface RecruiterService {

	String postJob(JobRequest jobRequest, String recruiterEmail);
}