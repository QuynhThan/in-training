package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
public class BSPatternDTO {
    private Long bSPatternId;
    private Boolean status;
    private Boolean isDelete;
    private List<BCriteriaSubDTO> criteriaSubs;


}
