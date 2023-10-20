package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
