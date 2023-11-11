package com.ms.training.application.dto.training;


import com.ms.training.domain.entities.training.enumreration.ERDType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardDisciplineDTO {
    private Long rewardDisciplineId;
    private ERDType eRDType;
    private String content;
    private StudentDTO student;
    private SemesterDTO semester;

}
