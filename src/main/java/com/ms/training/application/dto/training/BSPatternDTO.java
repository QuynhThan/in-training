package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BSPatternDTO {
    private Long bSPatternId;
    private Boolean status;
    private Boolean isDelete;
    private List<BCriteriaSubDTO> criteriaSubs;


}
