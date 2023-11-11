package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BCriteriaSubDTO {
    private Long bCriteriaSubId;
    private String name;
    private String description;
    private Integer max_point;
    private Boolean isDelete;
    private BCriteriaDTO bCriteria;
    private List<BSPatternDTO> bSPatterns;
}
