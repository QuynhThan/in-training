package com.ms.training.application.dto.training;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
public class FacultyDTO {
    private Long facultyId;
    private String name;
    private List<MyClassDTO> classes;
}
