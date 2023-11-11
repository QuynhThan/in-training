package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Chuong trinh giang day
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDTO {
    private Long curriculumId;
    private String name;
    private Integer numSubject;
    private FacultyDTO faculty;
    private List<SubjectDTO> subjects;
}
