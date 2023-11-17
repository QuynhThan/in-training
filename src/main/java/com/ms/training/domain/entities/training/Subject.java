package com.ms.training.domain.entities.training;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_num")
    private Integer creditNum;

    @Column(name = "theory_num")
    private Integer theoryNum;

    @Column(name = "practical_num")
    private Integer practicalNum;

    @Column(name = "academic_year")
    private String academicYear;

    @ManyToOne
    @JoinColumn(name = "prerequisite_id")
    private Subject prerequisite;

    @ManyToMany(mappedBy = "subjects")
    private List<Curriculum> curriculums;

   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "components_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private List<ComponentPoint> componentPoints;
//
//    @OneToMany(fetch=FetchType.LAZY,mappedBy = "subject",cascade=CascadeType.ALL)
//    private List<ComponentSubject> componentSubjects;


}
