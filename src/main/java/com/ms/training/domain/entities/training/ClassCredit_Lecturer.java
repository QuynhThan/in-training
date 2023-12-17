package com.ms.training.domain.entities.training;

import lombok.Data;

import javax.persistence.*;

//@Entity
//@Table(name = "class_credit_lecturer")
//@Data
public class ClassCredit_Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_credit_id")
    private Long classCreditId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "lecturer_id",referencedColumnName = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "class_credit_id",referencedColumnName = "class_credit_id")
    private ClassCredit classCredit;
}
