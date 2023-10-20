package com.ms.training.application.dto.training;

import lombok.Data;

import java.util.List;

@Data
public class MyClassDTO {
    private Long classId;
    private String name;
    private LecturerDTO lecturer;
    private List<StudentDTO> students;
    private FacultyDTO faculty;

}
