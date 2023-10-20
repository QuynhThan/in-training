package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
