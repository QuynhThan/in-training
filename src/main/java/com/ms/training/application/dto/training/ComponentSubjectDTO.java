package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(ComponentSubjectId.class)
public class ComponentSubjectDTO {
    private Long id;
    private SubjectDTO subject;
    private ComponentPointDTO component;
    private Double percentNumber;
}
