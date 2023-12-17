package com.ms.training.domain.entities.training;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "class")
@Data
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id",referencedColumnName = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "aClass")
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_code",referencedColumnName = "faculty_code")
    private Faculty faculty;

}
