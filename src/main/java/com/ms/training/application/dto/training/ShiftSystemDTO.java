package com.ms.training.application.dto.training;

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
public class ShiftSystemDTO {
    private Integer shiftSystemId;
    private Date timeStart;
    private Date timeClose;
    private Boolean type;
    private List<TimeTableDTO> timeTable;

}
