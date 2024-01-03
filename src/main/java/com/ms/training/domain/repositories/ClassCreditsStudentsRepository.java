package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCredit;
import com.ms.training.domain.entities.training.ClassCreditsStudents;
import com.ms.training.domain.entities.training.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassCreditsStudentsRepository extends JpaRepository<ClassCreditsStudents, Long>, JpaSpecificationExecutor<ClassCreditsStudents> {
    ClassCreditsStudents findFirstByClassCreditAndStudent(ClassCredit classCredit, Student student);
    ClassCreditsStudents findFirstByStudentAndStatusAndClassCreditIn(Student student, String status, List<ClassCredit> classCreditList);
    List<ClassCreditsStudents> findAllByClassCredit(ClassCredit classCredit);
    List<ClassCreditsStudents> findAllByClassCreditAndStatus(ClassCredit classCredit, String status);

}
