package com.ms.training.application.dto.training;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDTO {
    private Long employeeId;
    private ProfileDTO profile;
    private AccountDTO account;
}
