package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Curriculum;
import com.ms.training.domain.entities.training.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Curriculum findFirstByFaculty(Faculty faculty);
}
