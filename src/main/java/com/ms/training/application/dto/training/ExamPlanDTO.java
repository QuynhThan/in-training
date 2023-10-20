package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class ExamPlanDTO {
    private Long examPlanId;
    private Date regisOpening;
    private Date regisClosing;
    private Date dateStart;
    private Date dateEnd;
    private String title;
    private Boolean isDelete;
    private SemesterDTO semester;
    private List<ExamDTO> exams;

}
