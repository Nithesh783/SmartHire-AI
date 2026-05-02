package com.smarthire.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SkillExtractor {

	private static final String[] SKILLS_DATABASE = { "Java", "Spring", "Spring Boot", "Hibernate", "JPA", "SQL",
			"MySQL", "PostgreSQL", "REST API", "Microservices", "React", "Angular", "JavaScript", "HTML", "CSS",
			"Bootstrap", "Docker", "AWS", "Git", "Maven" };

	public String extractSkills(String resumeText) {

		List<String> foundSkills = new ArrayList<>();

		String lowerCaseResume = resumeText.toLowerCase();

		for (String skill : SKILLS_DATABASE) {

			if (lowerCaseResume.contains(skill.toLowerCase())) {
				foundSkills.add(skill);
			}
		}

		if (foundSkills.isEmpty()) {
			return "No matching skills found";
		}

		return String.join(", ", foundSkills);
	}
}