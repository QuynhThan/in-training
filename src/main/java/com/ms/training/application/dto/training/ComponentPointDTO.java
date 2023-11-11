package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentPointDTO {
    private Long componentId;
    private String name;
    private String description;
//    private List<SubjectDTO> subjects;
}
