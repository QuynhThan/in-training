package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private ProfileDTO profile;
    private AccountDTO account;
}
