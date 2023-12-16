package com.ms.training.application.dto.training;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDTO {
    private Long id;
    private Long timeTableId;
    private LocalDateTime lessonDate;
    private Boolean status;
    private ClassCreditDTO classCredit;
    private ClassroomDTO classroom;
    private List<ShiftSystemDTO> shiftSystems;
    private List<StudentDTO> students;
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private int index;
}
