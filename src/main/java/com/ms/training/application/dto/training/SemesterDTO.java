package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
public class SemesterDTO {
    private Long semesterId;
    private Integer year;
    private String name;
    private Integer num;
    private List<BehaviorSheetDTO> bSheets;
}
