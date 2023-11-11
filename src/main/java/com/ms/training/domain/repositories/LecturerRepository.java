package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LecturerRepository extends JpaRepository<Lecturer, Long>, JpaSpecificationExecutor<Lecturer> {
}
