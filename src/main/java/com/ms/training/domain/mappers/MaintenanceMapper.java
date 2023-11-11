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
    Lecturer toLecturers(LecturerDTO dto);
    Classroom toClassroom(ClassroomDTO dto);
    ClassCredit toClassCredit(ClassCreditDTO dto);
    StudentDTO toStudentDTO(Student student);
    List<StudentDTO> toStudentDTOs(List<Student> students);
    LecturerDTO toLectureDTO(Lecturer lecturer);
    List<LecturerDTO> toLectureDTO(List<Lecturer> lecturers);
}
