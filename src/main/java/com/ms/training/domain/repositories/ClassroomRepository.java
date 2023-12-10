package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>, JpaSpecificationExecutor<Classroom> {
    List<Classroom> findAllByClassroomIdIn(List<Long> ids);
}
