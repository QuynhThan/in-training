package com.ms.training.domain.entities.training;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "semester")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id")
    private Long semesterId;

//    @JsonFormat(pattern = "dd/MM/yyyy'-'HH:mm", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "year")
    private Integer year;

    @Column(name = "name")
    private String name;

    @Column(name = "num")
    private Integer num;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "semester")
    private List<BehaviorSheet> bSheets;
}
