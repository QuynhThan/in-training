package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCreditGroupDTO {
    private Long id;
    private String groupNumber;
    private String subGroup;
    private Long noOfStudent;
    private ClassCreditDTO classCredit;
    private List<ClassCreditsStudentsDTO> students;

}
