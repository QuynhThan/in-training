package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCredit;
import com.ms.training.domain.entities.training.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassCreditRepository extends JpaRepository<ClassCredit, Long>, JpaSpecificationExecutor<ClassCredit> {
    List<ClassCredit> findAllBySubject(Subject subject);
    List<ClassCredit> findAllBySubjectAndStatus(Subject subject, String status);
    List<ClassCredit> findAllByStatus(String status);
}
