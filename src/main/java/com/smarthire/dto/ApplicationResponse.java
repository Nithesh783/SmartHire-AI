package com.smarthire.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private String candidateName;
    private String candidateEmail;
    private String jobTitle;
    private double matchScore;
    private String extractedSkills;
    private LocalDateTime appliedAt;
}