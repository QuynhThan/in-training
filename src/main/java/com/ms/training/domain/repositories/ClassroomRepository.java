package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
