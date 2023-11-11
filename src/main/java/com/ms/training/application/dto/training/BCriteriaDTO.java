package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class BCriteriaDTO {
    private Long bCriteriaId;
    private String name;
    private Double maxPoint;
    private List<BCriteriaSubDTO> criteriaSubs;
}
