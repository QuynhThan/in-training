package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor<Class> {
    List<Class> findAllByClassIdIn(List<Long> ids);
}
