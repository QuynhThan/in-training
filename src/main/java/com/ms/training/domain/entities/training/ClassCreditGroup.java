//package com.ms.training.domain.entities.training;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "class_credit_group")
//@Data
//public class ClassCreditGroup {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "group_id")
//    private Long id;
//
//    @Column(name = "group_number")
//    private String groupNumber;
//
//    @Column(name = "sub_group")
//    private String subGroup;
//
//    @Column(name = "no_of_student")
//    private Long noOfStudent;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "class_credit_id")
//    private ClassCredit classCredit;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classCreditGroup")
//    private List<ClassCreditsStudents> students;
//
//}
