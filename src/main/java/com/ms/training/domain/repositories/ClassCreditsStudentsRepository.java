package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCreditsStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassCreditsStudentsRepository extends JpaRepository<ClassCreditsStudents, Long>, JpaSpecificationExecutor<ClassCreditsStudents> {
}
