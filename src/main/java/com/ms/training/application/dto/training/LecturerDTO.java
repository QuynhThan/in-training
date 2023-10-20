package com.ms.training.application.dto.training;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
public class LecturerDTO {
    private Long lecturerId;
    private FacultyDTO faculty;
    private ProfileDTO profile;
    private AccountDTO account;
    private List<MyClassDTO> classes;
    private List<ExamDTO> exams;

}
