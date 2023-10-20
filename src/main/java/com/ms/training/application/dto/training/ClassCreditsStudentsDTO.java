package com.ms.training.application.dto.training;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassCreditsStudentsDTO {
    private Long id;
    private ClassCreditDTO classCredit;
    private StudentDTO student;
    private Boolean status;

}
