package com.ms.training.application.dto.training;


import com.ms.training.domain.entities.training.enumreration.ERDType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
public class RewardDisciplineDTO {
    private Long rewardDisciplineId;
    private ERDType eRDType;
    private String content;
    private StudentDTO student;
    private SemesterDTO semester;

}
