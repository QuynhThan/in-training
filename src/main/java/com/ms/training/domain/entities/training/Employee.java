package com.ms.training.domain.entities.training;


import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "profile_id",referencedColumnName = "profile_id")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id")
    private Account account;
}
