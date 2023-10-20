package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class ShiftSystemDTO {
    private Integer shiftSystemId;
    private Date timeStart;
    private Date timeClose;
    private Boolean type;
    private List<TimeTableDTO> timeTable;

}
