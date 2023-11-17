package com.ms.training.domain.service.Impl;

import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.search.SearchSpecification;
import com.ms.training.application.dto.training.*;
import com.ms.training.application.exception.BusinessException;
import com.ms.training.domain.entities.training.*;
import com.ms.training.domain.mappers.MaintenanceMapper;
import com.ms.training.domain.repositories.*;
import com.ms.training.domain.service.MaintenanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MaintenanceDataImpl implements MaintenanceData {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    ClassCreditRepository classCreditRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new BusinessException("", "Cannot save Subject!");
        }
        return MaintenanceMapper.INSTANCE.toSubjectDTO(subject);
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
        if (Objects.isNull(subjectDTO.getSubjectId())) {
            throw new BusinessException("", "subject id cannot null in update");
        }
        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new BusinessException("","Cannot update subject");
        }

        return subjectDTO;
    }

    @Override
    public SubjectDTO deleteSubject(SubjectDTO subjectDTO) {
        if (Objects.isNull(subjectDTO.getSubjectId())) {
            throw new BusinessException("", "subject id cannot null in delete");
        }
        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        try {
            subjectRepository.delete(subject);
        } catch (Exception e) {
            throw new BusinessException("","Cannot delete subject");
        }
        return subjectDTO;
    }

    @Override
    public LecturerDTO deleteLecturers(LecturerDTO lecturerDTO) {
        Lecturer lecturer = MaintenanceMapper.INSTANCE.toLecturers(lecturerDTO);
        try {
            lecturerRepository.delete(lecturer);
        } catch (Exception e) {
            throw new BusinessException("","cannot delete lecturers");
        }
        return lecturerDTO;
    }

    @Override
    public LecturerDTO addLecturers(LecturerDTO lecturerDTO) {
        Lecturer lecturer = MaintenanceMapper.INSTANCE.toLecturers(lecturerDTO);
        try {
            lecturerRepository.save(lecturer);
        } catch (Exception e) {
            throw new BusinessException("","cannot save lecturers");
        }

        return lecturerDTO;
    }

    @Override
    public ClassroomDTO addClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = MaintenanceMapper.INSTANCE.toClassroom(classroomDTO);
        try {
            classroomRepository.save(classroom);
        } catch (Exception e) {
            throw new BusinessException("","cannot save classroom");
        }
        return classroomDTO;
    }

    @Override
    public ClassroomDTO deleteClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = MaintenanceMapper.INSTANCE.toClassroom(classroomDTO);
        try {
            classroomRepository.delete(classroom);
        } catch (Exception e) {
            throw new BusinessException("","cannot delete classroom");
        }
        return classroomDTO;
    }

    @Override
    public ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO) {
        ClassCredit classCredit = MaintenanceMapper.INSTANCE.toClassCredit(classCreditDTO);
        try {
            classCreditRepository.save(classCredit);
        } catch (Exception e) {
            throw new BusinessException("","cannot save class credit");
        }
        return classCreditDTO;
    }

    @Override
    public ClassCreditDTO deleteClassCredit(ClassCreditDTO classCreditDTO) {
        ClassCredit classCredit = MaintenanceMapper.INSTANCE.toClassCredit(classCreditDTO);
        try {
            classCreditRepository.delete(classCredit);
        } catch (Exception e) {
            throw new BusinessException("","cannot delete class credit");
        }

        return classCreditDTO;
    }

    @Override
    public StudentDTO getStudent(Long id) {
        return MaintenanceMapper.INSTANCE.toStudentDTO(studentRepository.findById(id).orElse(null));
    }

    @Override
    public List<StudentDTO> getAllStudentBy(SearchRequest request) {
        SearchSpecification<Student> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Student> entities = studentRepository.findAll(specification, pageable);
        return entities.map(MaintenanceMapper.INSTANCE::toStudentDTO).stream().toList();
    }

    @Override
    public LecturerDTO getLecture(Long id) {
        return MaintenanceMapper.INSTANCE.toLectureDTO(lecturerRepository.findById(id).orElse(null));
    }

    @Override
    public List<LecturerDTO> getLectures(SearchRequest request) {
        SearchSpecification<Lecturer> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Lecturer> entities = lecturerRepository.findAll(specification, pageable);
        return entities.map(MaintenanceMapper.INSTANCE::toLectureDTO).stream().toList();
    }

    @Override
    public List<SubjectDTO> subjectRetrieve(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Subject> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Subject> entities = subjectRepository.findAll(specification, pageable);
        return entities.map(MaintenanceMapper.INSTANCE::toSubjectDTO).stream().toList();
    }


}
