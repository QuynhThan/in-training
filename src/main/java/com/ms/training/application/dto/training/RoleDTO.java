package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long roleId;
    private String name;
    private Set<AccountDTO> accounts;

}
