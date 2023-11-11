package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorSheetDTO {
    private Long bSheetId;
    private StudentDTO student;
    private SemesterDTO semester;
    private BSPatternDTO bSPattern;
}
