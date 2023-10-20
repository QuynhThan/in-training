package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassCreditRepository extends JpaRepository<ClassCredit, Long> {
}
