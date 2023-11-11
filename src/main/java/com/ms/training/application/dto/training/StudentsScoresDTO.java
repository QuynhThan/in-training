package com.ms.training.application.dto.training;


import com.ms.training.domain.entities.training.id.StudentScoreID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsScoresDTO {
    private ClassCreditsStudentsDTO regisClass;
    private ComponentSubjectDTO componentSubject;
    private Double pointNumber;
}
