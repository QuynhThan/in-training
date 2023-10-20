package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ComponentPointDTO {
    private Long componentId;
    private String name;
    private String description;
    private List<SubjectDTO> subjects;
}
