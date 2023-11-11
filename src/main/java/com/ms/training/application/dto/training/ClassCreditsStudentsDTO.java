package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreditsStudentsDTO {
    private Long id;
    private ClassCreditDTO classCredit;
    private StudentDTO student;
    private Boolean status;

}
