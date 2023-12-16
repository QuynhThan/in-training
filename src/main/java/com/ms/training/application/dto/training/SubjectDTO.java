package com.ms.training.application.dto.training;

import com.ms.training.domain.entities.training.Lecturer;
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
    private String subjectCode;
    private String name;
    private Integer creditNum;
    private Integer theoryNum;
    private Integer practicalNum;
    private String academicYear;
    private SubjectDTO prerequisite;
    private String prerequisiteCode;
//    private List<CurriculumDTO> curriculums;
    private List<ComponentPointDTO> componentPoints;
//
//    @OneToMany(fetch=FetchType.LAZY,mappedBy = "subject",cascade=CascadeType.ALL)
//    private List<ComponentSubject> componentSubjects;
    // phan mon
    private boolean isPhanMon;
    private String listGVStr;
    private List<Long> listGV;
    private List<LecturerDTO> lecturers;
    private Long khoa;
}
