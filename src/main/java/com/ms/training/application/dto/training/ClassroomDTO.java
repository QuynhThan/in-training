package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private Long classroomId;
    private String name;
    private String roomType;
    private Integer maxSize;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classroom")
//    private List<Exam> examList;
}
