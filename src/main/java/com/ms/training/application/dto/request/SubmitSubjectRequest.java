package com.ms.training.application.dto.request;

import com.ms.training.application.dto.training.ClassCreditDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitSubjectRequest {
    private Long registeredId;
    private String mssv;
    private Long studentId;
    private Long classCreditId;
    private Long ccGroupId;
    private Boolean status;
    private String userName;
    private ClassCreditDTO classCredit;
}
