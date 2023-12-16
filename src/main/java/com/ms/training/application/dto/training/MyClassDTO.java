package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyClassDTO {
    private Long classId;
    private String name;
    private LecturerDTO lecturer;
//    private List<StudentDTO> students;
    private FacultyDTO faculty;

}
