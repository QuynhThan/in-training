package com.ms.training.application.dto.training;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
public class BCriteriaSubDTO {
    private Long bCriteriaSubId;
    private String name;
    private String description;
    private Integer max_point;
    private Boolean isDelete;
    private BCriteriaDTO bCriteria;
    private List<BSPatternDTO> bSPatterns;
}
