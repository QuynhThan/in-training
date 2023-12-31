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
public class SemesterDTO {
    private Long semesterId;
    private Integer year;
    private String name;
    private Integer num;
    private Date regisOpening;
    private Date regisClosing;
    private Date dateStart;
//    private List<BehaviorSheetDTO> bSheets;
}
