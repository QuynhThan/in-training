package com.ms.training.domain.service.Impl;

import com.ms.training.application.dto.request.SubmitSubjectRequest;
import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.domain.entities.training.ClassCredit;
import com.ms.training.domain.entities.training.ClassCreditGroup;
import com.ms.training.domain.entities.training.ClassCreditsStudents;
import com.ms.training.domain.entities.training.Student;
import com.ms.training.domain.repositories.ClassCreditGroupRepository;
import com.ms.training.domain.repositories.ClassCreditRepository;
import com.ms.training.domain.repositories.ClassCreditsStudentsRepository;
import com.ms.training.domain.repositories.StudentRepository;
import com.ms.training.domain.service.InTrainingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InTrainingDataImpl implements InTrainingData {
    @Autowired
    ClassCreditRepository classCreditRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassCreditGroupRepository classCreditGroupRepository;
    @Autowired
    ClassCreditsStudentsRepository classCreditsStudentsRepository;



    @Override
    public ClassCreditDTO submitRegistration(SubmitSubjectRequest request) {
        if (Objects.nonNull(request.getRegisteredId())) {
            ClassCreditsStudents classCreditsStudents = classCreditsStudentsRepository.findById(request.getRegisteredId()).orElse(null);
            if (Objects.nonNull(classCreditsStudents)) {
                classCreditsStudents.setStatus(request.getStatus());
                classCreditsStudentsRepository.save(classCreditsStudents);
                return ClassCreditDTO.builder().status("SUCCESS").build();
            }
        }
        Student student = studentRepository.findById(request.getStudentId()).orElse(null);
//        ClassCredit classCredit = classCreditRepository.findById(request.getClassCreditId()).orElse(null);
        ClassCreditGroup classCreditGroup = classCreditGroupRepository.findById(request.getCcGroupId()).orElse(null);
        if (Objects.isNull(classCreditGroup) || Objects.isNull(student)) {
            throw new RuntimeException("Student or LTC not found!!");
        }
        ClassCreditsStudents classCreditsStudents = new ClassCreditsStudents();
        classCreditsStudents.setStudent(student);
        classCreditsStudents.setClassCreditGroup(classCreditGroup);
        classCreditsStudents.setStatus(request.getStatus());
        classCreditsStudentsRepository.save(classCreditsStudents);
        return ClassCreditDTO.builder().status("SUCCESS").build();
    }
}
