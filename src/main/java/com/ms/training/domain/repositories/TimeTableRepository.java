package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, JpaSpecificationExecutor<TimeTable> {
}
