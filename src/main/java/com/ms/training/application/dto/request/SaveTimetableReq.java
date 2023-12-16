package com.ms.training.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.training.application.dto.training.TimeTableDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTimetableReq {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private List<TimeTableDTO> timeTables;
    private List<LocalDateTime> startDates;
    private List<LocalDateTime> endDates;
}
