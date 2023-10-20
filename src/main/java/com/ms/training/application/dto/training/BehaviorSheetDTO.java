package com.ms.training.application.dto.training;


import lombok.Data;


@Data
public class BehaviorSheetDTO {
    private Long bSheetId;
    private StudentDTO student;
    private SemesterDTO semester;
    private BSPatternDTO bSPattern;
}
