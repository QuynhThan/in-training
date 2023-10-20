package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
//@IdClass(ComponentSubjectId.class)
public class ComponentSubjectDTO {
    private Long id;
    private SubjectDTO subject;
    private ComponentPointDTO component;
    private Double percentNumber;
}
