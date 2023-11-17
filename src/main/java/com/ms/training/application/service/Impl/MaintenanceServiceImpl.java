package com.ms.training.application.service.Impl;

import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.application.dto.training.ClassroomDTO;
import com.ms.training.application.dto.training.LecturerDTO;
import com.ms.training.application.dto.training.SubjectDTO;
import com.ms.training.application.exception.BusinessException;
import com.ms.training.application.service.MaintenanceService;
import com.ms.training.domain.service.MaintenanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    MaintenanceData maintenanceData;

    private void checkValidRequest(Long id) {
        if (Objects.isNull(id)) {
            throw new BusinessException("","Id cannot null!!");
        }
    }

    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        return maintenanceData.addSubject(subjectDTO);
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
        return maintenanceData.updateSubject(subjectDTO);
    }

    @Override
    public SubjectDTO deleteSubject(SubjectDTO subjectDTO) {
        return maintenanceData.deleteSubject(subjectDTO);
    }

    @Override
    public LecturerDTO deleteLecturers(LecturerDTO lecturerDTO) {
        return maintenanceData.deleteLecturers(lecturerDTO);
    }

    @Override
    public LecturerDTO addLecturers(LecturerDTO lecturerDTO) {
        return maintenanceData.addLecturers(lecturerDTO);
    }

    @Override
    public LecturerDTO updateLecturers(LecturerDTO lecturerDTO) {
        checkValidRequest(lecturerDTO.getLecturerId());
        return maintenanceData.addLecturers(lecturerDTO);
    }

    @Override
    public ClassroomDTO addClassroom(ClassroomDTO classroomDTO) {
        return maintenanceData.addClassroom(classroomDTO);
    }

    @Override
    public ClassroomDTO updateClassroom(ClassroomDTO classroomDTO) {
        checkValidRequest(classroomDTO.getClassroomId());
        return maintenanceData.addClassroom(classroomDTO);
    }

    @Override
    public ClassroomDTO deleteClassroom(ClassroomDTO classroomDTO) {
        checkValidRequest(classroomDTO.getClassroomId());
        return maintenanceData.deleteClassroom(classroomDTO);
    }

    @Override
    public ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO) {
        return maintenanceData.addClassCredit(classCreditDTO);
    }

    @Override
    public ClassCreditDTO updateClassCredit(ClassCreditDTO classCreditDTO) {
        checkValidRequest(classCreditDTO.getClassCreditId());
        return maintenanceData.addClassCredit(classCreditDTO);
    }

    @Override
    public ClassCreditDTO deleteClassCredit(ClassCreditDTO classCreditDTO) {
        return maintenanceData.deleteClassCredit(classCreditDTO);
    }

    @Override
    public List<SubjectDTO> subjectRetrieve(SearchRequest request) {
        return maintenanceData.subjectRetrieve(request);
    }
}
