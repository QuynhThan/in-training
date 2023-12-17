package com.ms.training.domain.entities.training;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @Column(name = "regis_opening")
    private Date regisOpening;

    @Column(name = "regis_closing")
    private Date regisClosing;

    @Column(name = "date_start")
    private Date dateStart;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "semester")
    private List<BehaviorSheet> bSheets;
}
