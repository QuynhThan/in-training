package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Account;
import com.ms.training.domain.entities.training.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByAccount(Account account);
}
