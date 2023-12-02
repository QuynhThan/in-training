package com.ms.training.application.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long profileId;
    private String profileCode;
    private String fullName;
    private Date DOB;
    private Boolean gender;
    private String email;
    private String phone;
//    private MatriculateStudentDTO matriculateStudent;
    private String address;
    private String qualification;
}
