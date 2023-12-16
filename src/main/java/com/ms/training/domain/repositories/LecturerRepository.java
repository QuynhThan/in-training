package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Account;
import com.ms.training.domain.entities.training.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long>, JpaSpecificationExecutor<Lecturer> {
    List<Lecturer> findByLecturerIdIn(List<Long> ids);
    Lecturer findByAccount(Account account);
    Lecturer findByAccount_Username(String username);
}
