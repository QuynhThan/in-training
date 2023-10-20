package com.ms.training.application.dto.training;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
public class ProfileDTO {
    private Long profileId;
    private String fullame;
    private Date DOB;
    private Boolean gender;
    private String email;
    private String phone;
    private MatriculateStudentDTO matriculateStudent;
    private String address;
    private String qualification;
}
