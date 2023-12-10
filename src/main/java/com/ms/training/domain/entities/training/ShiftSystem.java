package com.ms.training.domain.entities.training;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shift_system")
@Data
public class ShiftSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_system_id")
    private Long shiftSystemId;

    @Column(name = "timeStart")
    private LocalDateTime timeStart;

    @Column(name = "timeClose")
    private LocalDateTime timeClose;

    @Column(name = "type")
    private Boolean type;

    @ManyToMany(mappedBy = "shiftSystems")
    private List<TimeTable> timeTable;

}
