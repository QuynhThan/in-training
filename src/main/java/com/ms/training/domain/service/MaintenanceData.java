package com.ms.training.domain.service;

import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.training.*;

import java.util.List;

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

    StudentDTO getStudent(Long id);

    List<StudentDTO> getAllStudentBy(SearchRequest searchRequest);

    LecturerDTO getLecture(Long id);

    List<LecturerDTO> getLectures(SearchRequest request);
}
