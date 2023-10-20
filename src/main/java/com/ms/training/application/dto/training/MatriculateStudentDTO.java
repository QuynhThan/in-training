package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;


// sinh vien trung tuyen
@Builder
@Data
public class MatriculateStudentDTO {
    private String citizenIdentity;
    private Integer combinedExamId;
    private Double resultScore;


}
