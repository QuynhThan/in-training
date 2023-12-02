package com.ms.training.domain.mappers;

import com.ms.training.application.dto.training.*;
import com.ms.training.domain.entities.training.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MaintenanceMapper {
    public static MaintenanceMapper INSTANCE = Mappers.getMapper(MaintenanceMapper.class);

    Subject toSubject(SubjectDTO dto);
    List<SubjectDTO> toSubjectDTOs(List<Subject> subjects);
    SubjectDTO toSubjectDTO(Subject subject);
    Lecturer toLecturers(LecturerDTO dto);
    Classroom toClassroom(ClassroomDTO dto);
    ClassroomDTO toClassroomDTO(Classroom classroom);
    ClassCredit toClassCredit(ClassCreditDTO dto);
    ClassCreditDTO toClassCreditDTO(ClassCredit classCredit);
    StudentDTO toStudentDTO(Student student);
    List<StudentDTO> toStudentDTOs(List<Student> students);
    LecturerDTO toLectureDTO(Lecturer lecturer);
    FacultyDTO toFacultyDTO(Faculty faculty);
    List<LecturerDTO> toLectureDTO(List<Lecturer> lecturers);
}
