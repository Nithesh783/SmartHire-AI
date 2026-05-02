package com.smarthire.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private String location;
    private String experienceLevel;
    private String recruiterName;
    private String recruiterEmail;
    private LocalDateTime createdAt;
    private boolean active;
}