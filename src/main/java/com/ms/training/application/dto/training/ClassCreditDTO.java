package com.ms.training.application.dto.training;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClassCreditDTO {
    private Long classCreditId;
    private Integer groupNumber;
    private Integer minSize;
    private Integer maxSize;
    private Boolean status;
    private Date regisOpening;
    private Date regisClosing;
    private SubjectDTO subject;
    private SemesterDTO semester;
    private LecturerDTO lecturer;
    private FacultyDTO faculty;
    private List<ExamDTO> examList;
    private List<StudentDTO> students;

}
