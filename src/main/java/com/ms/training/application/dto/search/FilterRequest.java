package com.ms.training.application.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.training.domain.enums.FieldType;
import com.ms.training.domain.enums.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRequest {
    private String key;

    private Operator operator;

    private FieldType fieldType;

    private Object value;

    private Object valueTo;

    private List<Object> values;
}
