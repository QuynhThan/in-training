package com.ms.training.domain.entities.training;


import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "classcredits_students")
@Data
public class ClassCreditsStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_credit_id")
    private ClassCredit classCredit;

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private ClassCreditGroup classCreditGroup;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "status")
    private String status;

}
