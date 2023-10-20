package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import java.util.List;

// Chuong trinh giang day
@Builder
@Data
public class CurriculumDTO {
    private Long curriculumId;
    private String name;
    private Integer numSubject;
    private FacultyDTO faculty;
    private List<SubjectDTO> subjects;
}
