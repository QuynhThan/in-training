package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDTO {
    private Long timeTableId;
    private Date lessonDate;
    private Boolean status;
    private ClassCreditDTO classCredit;
    private ClassroomDTO classroom;
    private List<ShiftSystemDTO> shiftSystems;
    private List<StudentDTO> students;
}