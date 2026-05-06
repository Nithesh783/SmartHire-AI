package com.smarthire.dto;

public class ResumeResponse {

	private String message;
	private String fileName;
	private boolean status;
	private String skills;

	public ResumeResponse() {
	}

	public ResumeResponse(String message, String fileName, boolean status, String skills) {
		this.message = message;
		this.fileName = fileName;
		this.status = status;
		this.skills = skills;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}