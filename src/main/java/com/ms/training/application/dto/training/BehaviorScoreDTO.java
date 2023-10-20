package com.ms.training.application.dto.training;

import lombok.Data;


@Data
public class BehaviorScoreDTO {
    private BehaviorSheetDTO bSheet;
    private BCriteriaSubDTO bCriteriaSub;
    private Integer selfPoint;
    private Integer classPoint;
    private Integer advisorPoint;


}
