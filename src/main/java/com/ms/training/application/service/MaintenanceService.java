package com.ms.training.application.service;

import com.ms.training.application.dto.request.SaveTimetableReq;
import com.ms.training.application.dto.request.SubmitTimeTableReq;
import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.training.*;
import org.springframework.http.HttpStatus;

import java.util.List;

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

    List<SubjectDTO> subjectRetrieve(SearchRequest request);

    List<LecturerDTO> lecturerRetrieve(SearchRequest request);

    List<ClassroomDTO> classroomRetrieve(SearchRequest request);

    List<ClassCreditDTO> classCreditRetrieve(SearchRequest request);

    List<FacultyDTO> facultyRetrieve(SearchRequest request);

    Object submitTimeTable(SubmitTimeTableReq req);

    List<StudentClassDTO> studentClassRetrieve(SearchRequest request);

    List<TimeTableDTO> saveTimetable(SaveTimetableReq req);

    List<TimeTableDTO> retrieveTimetable(SearchRequest req);
}
