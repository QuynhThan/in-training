package com.ms.training.domain.repositories;

import com.ms.training.application.dto.search.SearchSpecification;
import com.ms.training.domain.entities.training.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    Subject findFirstBySubjectCode(String subjectCode);
    List<Subject> findSubjectsBySubjectIdIn(List<Long> ids);
}
