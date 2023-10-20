package com.ms.training.domain.entities.training.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BSheetBCriteriaSubId implements Serializable {
    private Long bSheet;
    private Long bCriteriaSub;
}
