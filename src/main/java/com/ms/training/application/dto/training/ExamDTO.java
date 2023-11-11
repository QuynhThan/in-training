package com.ms.training.application.dto.training;

import com.ms.training.domain.entities.training.enumreration.EExamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
