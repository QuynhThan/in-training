package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SemesterRepository extends JpaRepository<Semester, Long>, JpaSpecificationExecutor<Semester> {
    Semester findFirstByYearAndNum(Integer year, Integer num);
}
