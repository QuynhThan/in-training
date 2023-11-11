package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
