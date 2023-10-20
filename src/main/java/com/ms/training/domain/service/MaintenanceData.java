package com.ms.training.domain.service;

import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.application.dto.training.ClassroomDTO;
import com.ms.training.application.dto.training.LecturerDTO;
import com.ms.training.application.dto.training.SubjectDTO;

public interface MaintenanceData {
    SubjectDTO addSubject(SubjectDTO subjectDTO);

    SubjectDTO updateSubject(SubjectDTO subjectDTO);

    SubjectDTO deleteSubject(SubjectDTO subjectDTO);

    LecturerDTO deleteLecturers(LecturerDTO lecturerDTO);

    LecturerDTO addLecturers(LecturerDTO lecturerDTO);

    ClassroomDTO addClassroom(ClassroomDTO classroomDTO);

    ClassroomDTO deleteClassroom(ClassroomDTO classroomDTO);

    ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO);

    ClassCreditDTO deleteClassCredit(ClassCreditDTO classCreditDTO);
}
