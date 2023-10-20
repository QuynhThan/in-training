package com.ms.training.application.dto.training;

import com.ms.training.domain.entities.training.enumreration.EExamType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class ExamDTO {
    private Long examId;
    private Date examDate;
    private Date examTime;
    private Integer groupNumber;
    private EExamType examType;
    private ExamPlanDTO examPlan;
    private ClassCreditDTO classCredit;
    private ClassroomDTO classroom;
    private ShiftSystemDTO shiftSystem;
    private List<LecturerDTO> lecturers;


}
