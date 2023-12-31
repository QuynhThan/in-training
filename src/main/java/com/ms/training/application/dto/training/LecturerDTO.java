package com.ms.training.application.dto.training;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LecturerDTO {
    private Long lecturerId;
    private FacultyDTO faculty;
    private ProfileDTO profile;
    private AccountDTO account;
//    private List<MyClassDTO> classes;
//    private List<ExamDTO> exams;
    @JsonProperty("profile.fullName")
    private String fullName;
    @JsonProperty("profile.phone")
    private String phone;
    private boolean isUpdate;
}
