package com.ms.training.domain.entities.training;


import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "faculty")
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "faculty")
    private List<Class> classes;
}
