package com.ms.training.application.dto.training;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
public class StudentDTO {
    private Long studentId;
    private String academicYear;
    private ProfileDTO profile;
    private AccountDTO account;
    private List<BehaviorSheetDTO> bSheets;
    private List<ClassCreditDTO> classCredits;
    private MyClassDTO aClass;

}
