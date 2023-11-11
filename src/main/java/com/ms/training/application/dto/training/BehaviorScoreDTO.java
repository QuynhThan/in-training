package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorScoreDTO {
    private BehaviorSheetDTO bSheet;
    private BCriteriaSubDTO bCriteriaSub;
    private Integer selfPoint;
    private Integer classPoint;
    private Integer advisorPoint;


}
