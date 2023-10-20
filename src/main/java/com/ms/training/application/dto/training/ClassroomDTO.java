package com.ms.training.application.dto.training;

import lombok.Data;

import javax.persistence.*;

@Data
public class ClassroomDTO {
    private Long classroomId;
    private String name;
    private String roomType;
    private Integer maxSize;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classroom")
//    private List<Exam> examList;
}
