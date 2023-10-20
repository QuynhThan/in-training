package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class RoleDTO {
    private Long roleId;
    private String name;
    private Set<AccountDTO> accounts;

}
