package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ShiftSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftSystemRepository extends JpaRepository<ShiftSystem, Long> {
}
