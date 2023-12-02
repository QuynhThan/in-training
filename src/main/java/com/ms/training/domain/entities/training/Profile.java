package com.ms.training.domain.entities.training;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Entity
@Table(name = "profile",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "profile_code")
})
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_code")
    private String profileCode;

    @Column(name = "fullnanme")
    private String fullName;

    @Column(name = "DOB")
    private Date DOB;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

//    @Column(name = "citizen_identity")
//    private String  citizenIdentity;

    @OneToOne
    @JoinColumn(name = "citizen_identity", referencedColumnName = "citizen_identity", insertable = false, updatable = false)
    private MatriculateStudent matriculateStudent;

    @Column(name = "address")
    private String address;

    @Column(name = "qualification")
    private String qualification;
}
