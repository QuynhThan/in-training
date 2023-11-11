package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long subjectId;
    private String name;
    private Integer creditNum;
    private Integer theoryNum;
    private Integer practicalNum;
    private String academicYear;
    private SubjectDTO prerequisite;
//    private List<CurriculumDTO> curriculums;
    private List<ComponentPointDTO> componentPoints;
//
//    @OneToMany(fetch=FetchType.LAZY,mappedBy = "subject",cascade=CascadeType.ALL)
//    private List<ComponentSubject> componentSubjects;


}
