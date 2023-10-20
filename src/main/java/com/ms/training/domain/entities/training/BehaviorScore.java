package com.ms.training.domain.entities.training;

import javax.persistence.*;

import com.ms.training.domain.entities.training.id.BSheetBCriteriaSubId;
import lombok.Data;

@Entity
@Table(name = "behavior_score")
@IdClass(BSheetBCriteriaSubId.class)
@Data
public class BehaviorScore {
    @Id
    @ManyToOne
    @JoinColumn(name = "bsheet_id")
    private BehaviorSheet bSheet;

    @Id
    @ManyToOne
    @JoinColumn(name = "bcriteria_sub_id")
    private BCriteriaSub bCriteriaSub;

    @Column(name = "self_point")
    private Integer selfPoint;

    @Column(name = "class_point")
    private Integer classPoint;

    @Column(name = "advisor_point")
    private Integer advisorPoint;


}
