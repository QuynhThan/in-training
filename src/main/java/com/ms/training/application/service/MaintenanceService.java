package com.ms.training.application.service;

import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.application.dto.training.ClassroomDTO;
import com.ms.training.application.dto.training.LecturerDTO;
import com.ms.training.application.dto.training.SubjectDTO;

public interface MaintenanceService {
    SubjectDTO addSubject(SubjectDTO subjectDTO);

    SubjectDTO updateSubject(SubjectDTO subjectDTO);

    SubjectDTO deleteSubject(SubjectDTO subjectDTO);

    LecturerDTO deleteLecturers(LecturerDTO lecturerDTO);

    LecturerDTO addLecturers(LecturerDTO lecturerDTO);

    LecturerDTO updateLecturers(LecturerDTO lecturerDTO);

    ClassroomDTO addClassroom(ClassroomDTO classroomDTO);

    ClassroomDTO updateClassroom(ClassroomDTO classroomDTO);

    ClassroomDTO deleteClassroom(ClassroomDTO classroomDTO);

    ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO);

    ClassCreditDTO updateClassCredit(ClassCreditDTO classCreditDTO);

    ClassCreditDTO deleteClassCredit(ClassCreditDTO classCreditDTO);
}
