package com.smarthire.dto;

public class JobRequest {

	private String title;

	private String description;

	private String requiredSkills;

	private String experienceLevel;

	public JobRequest() {
	}

	public JobRequest(String title, String description, String requiredSkills, String experienceLevel) {
		this.title = title;
		this.description = description;
		this.requiredSkills = requiredSkills;
		this.experienceLevel = experienceLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
}