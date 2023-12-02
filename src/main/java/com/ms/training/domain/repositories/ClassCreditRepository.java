package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassCreditRepository extends JpaRepository<ClassCredit, Long>, JpaSpecificationExecutor<ClassCredit> {
}
