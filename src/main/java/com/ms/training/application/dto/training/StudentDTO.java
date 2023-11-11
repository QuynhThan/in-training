package com.ms.training.application.dto.training;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long studentId;
    private String academicYear;
    private ProfileDTO profile;
    private AccountDTO account;
    private List<BehaviorSheetDTO> bSheets;
    private List<ClassCreditDTO> classCredits;
    private MyClassDTO aClass;

}
