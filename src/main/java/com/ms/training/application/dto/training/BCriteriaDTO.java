package com.ms.training.application.dto.training;


import javax.persistence.*;
import java.util.List;

public class BCriteriaDTO {
    private Long bCriteriaId;
    private String name;
    private Double maxPoint;
    private List<BCriteriaSubDTO> criteriaSubs;
}
