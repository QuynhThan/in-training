package com.ms.training.domain.entities.training;


import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "academic_year")
    private String academicYear;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "profile_id",referencedColumnName = "profile_id")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student")
    private List<BehaviorSheet> bSheets;

//    @OneToMany(mappedBy = "students")
//    private List<ClassCredit> classCredits;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = )

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id",referencedColumnName = "class_id")
    private Class aClass;

}
