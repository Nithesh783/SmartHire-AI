package com.smarthire.service;

import org.springframework.web.multipart.MultipartFile;

import com.smarthire.dto.ResumeResponse;

public interface ResumeService {

	ResumeResponse uploadResume(MultipartFile file, String email);
}