package com.ms.training.application.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.training.domain.enums.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortRequest {

    private String key;

    private SortDirection direction;

}