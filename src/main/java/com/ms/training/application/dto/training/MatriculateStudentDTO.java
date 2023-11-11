package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// sinh vien trung tuyen
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculateStudentDTO {
    private String citizenIdentity;
    private Integer combinedExamId;
    private Double resultScore;


}
