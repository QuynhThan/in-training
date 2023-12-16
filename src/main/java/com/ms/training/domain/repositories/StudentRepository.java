package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Account;
import com.ms.training.domain.entities.training.Employee;
import com.ms.training.domain.entities.training.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Student findByAccount(Account account);
    Student findStudentByAccount_Username(String username);

}
