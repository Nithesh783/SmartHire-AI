package com.smarthire.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(length = 2000)
	private String description;

	private String requiredSkills;

	private String experienceLevel;

	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private User recruiter;

	public Job() {
	}

	public Job(Long id, String title, String description, String requiredSkills, String experienceLevel,
			LocalDateTime createdAt, User recruiter) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.requiredSkills = requiredSkills;
		this.experienceLevel = experienceLevel;
		this.createdAt = createdAt;
		this.recruiter = recruiter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(User recruiter) {
		this.recruiter = recruiter;
	}
}