package com.ms.training.domain.mappers;

import com.ms.training.application.dto.training.ClassCreditDTO;
import com.ms.training.application.dto.training.ClassroomDTO;
import com.ms.training.application.dto.training.LecturerDTO;
import com.ms.training.application.dto.training.SubjectDTO;
import com.ms.training.domain.entities.training.ClassCredit;
import com.ms.training.domain.entities.training.Classroom;
import com.ms.training.domain.entities.training.Lecturer;
import com.ms.training.domain.entities.training.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaintenanceMapper {
    public static MaintenanceMapper INSTANCE = Mappers.getMapper(MaintenanceMapper.class);

    Subject toSubject(SubjectDTO dto);
    Lecturer toLecturers(LecturerDTO dto);
    Classroom toClassroom(ClassroomDTO dto);
    ClassCredit toClassCredit(ClassCreditDTO dto);
}
