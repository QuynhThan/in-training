package com.ms.training.application.dto.training;


import com.ms.training.domain.entities.training.id.StudentScoreID;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
public class StudentsScoresDTO {
    private ClassCreditsStudentsDTO regisClass;
    private ComponentSubjectDTO componentSubject;
    private Double pointNumber;
}
