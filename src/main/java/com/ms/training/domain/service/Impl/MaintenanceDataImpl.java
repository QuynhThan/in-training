package com.ms.training.domain.service.Impl;

import com.ms.training.application.dto.request.SaveTimetableReq;
import geneticalgorithm.*;
import com.ms.training.application.dto.request.SubmitTimeTableReq;
import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.search.SearchSpecification;
import com.ms.training.application.dto.training.*;
import com.ms.training.application.exception.BusinessException;
import com.ms.training.domain.entities.training.*;
import com.ms.training.domain.entities.training.Class;
import com.ms.training.domain.entities.training.Classroom;
import com.ms.training.domain.enums.StatusEnum;
import com.ms.training.domain.mappers.MaintenanceMapper;
import com.ms.training.domain.repositories.*;
import com.ms.training.domain.service.MaintenanceData;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static geneticalgorithm.ScheduleAlgo.buildSchedule;
import static geneticalgorithm.ScheduleAlgo.initializeSchedule;

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

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ShiftSystemRepository shiftSystemRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    TimeTableRepository timeTableRepository;
    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        if (subjectDTO.isPhanMon()) {
            Subject mySubject = subjectRepository.findFirstBySubjectCode(subjectDTO.getSubjectCode());
            if (Objects.isNull(mySubject)) {
                throw new RuntimeException("Invalid Subject!!");
            }
            List<Lecturer> lecturers = lecturerRepository.findByLecturerIdIn(subjectDTO.getListGV());
            mySubject.setLecturers(lecturers);
            try {
                subjectRepository.save(mySubject);
            } catch (Exception exception) {
                throw new RuntimeException("Cannot save subject!!");
            }
            return MaintenanceMapper.INSTANCE.toSubjectDTO(mySubject);
        }

        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        if (Objects.nonNull(subjectRepository.findFirstBySubjectCode(subject.getSubjectCode()))) {
            throw new RuntimeException("Subject Code is existed!!");
        }
        if (Strings.isNotEmpty(subjectDTO.getPrerequisiteCode())) {
            subject.setPrerequisite(buildRreRequisiteSubject(subjectDTO.getPrerequisiteCode()));
        }
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
            throw new RuntimeException("subject id cannot null in update");
        }
        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        if (Strings.isNotEmpty(subjectDTO.getPrerequisiteCode())) {
            subject.setPrerequisite(buildRreRequisiteSubject(subjectDTO.getPrerequisiteCode()));
        }
        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new RuntimeException("Cannot update subject");
        }

        return subjectDTO;
    }

    @Override
    public SubjectDTO deleteSubject(SubjectDTO subjectDTO) {
        if (Objects.isNull(subjectDTO.getSubjectId())) {
            throw new RuntimeException("subject id cannot null in delete");
        }
        Subject subject = MaintenanceMapper.INSTANCE.toSubject(subjectDTO);
        try {
            subjectRepository.delete(subject);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete subject");
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
    @Transactional
    public LecturerDTO addLecturers(LecturerDTO lecturerDTO) {
        Lecturer lecturer = MaintenanceMapper.INSTANCE.toLecturers(lecturerDTO);
        try {
            accountRepository.saveAndFlush(lecturer.getAccount());
            profileRepository.saveAndFlush(lecturer.getProfile());
            lecturerRepository.saveAndFlush(lecturer);
        } catch (Exception e) {
            throw new BusinessException("","cannot save lecturers");
        }

        return MaintenanceMapper.INSTANCE.toLectureDTO(lecturer);
    }

    @Override
    public LecturerDTO updateLecturers(LecturerDTO lecturerDTO) {
        Lecturer lecturerReq = MaintenanceMapper.INSTANCE.toLecturers(lecturerDTO);
        Lecturer lecturer = lecturerRepository.findById(lecturerDTO.getLecturerId()).orElse(null);
        if (Objects.isNull(lecturer)) {
            throw new RuntimeException("Lecturer not found!!");
        }
        lecturer.setProfile(lecturerReq.getProfile());
        lecturer.setFaculty(lecturerReq.getFaculty());
        try {
            lecturerRepository.save(lecturer);
        } catch (Exception e) {
            throw new RuntimeException("cannot save lecturers");
        }

        return MaintenanceMapper.INSTANCE.toLectureDTO(lecturer);
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
    @Transactional
    public ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO) {
        ClassCredit classCredit = MaintenanceMapper.INSTANCE.toClassCredit(classCreditDTO);
        Subject subject = subjectRepository.findById(classCreditDTO.getSubjectId()).orElse(null);
        Lecturer lecturer = lecturerRepository.findById(classCreditDTO.getLecturerId()).orElse(null);
        Classroom classroom = classroomRepository.findById(classCreditDTO.getRoomId()).orElse(null);
        Semester semester = getSemester(classCreditDTO);
        if (Objects.isNull(subject) || Objects.isNull(classroom)) {
            throw new RuntimeException("Subject or Lecturer not found!!");
        }
        classCredit.setMaxSize(classroom.getMaxSize());
        classCredit.setLecturer(lecturer);
        classCredit.setSubject(subject);
        classCredit.setStatus(StatusEnum.INACTIVE.name());
        classCredit.setSemester(semester);
        classCredit.setClassrooms(List.of(classroom));
        try {
            classCreditRepository.save(classCredit);
        } catch (Exception e) {
            throw new BusinessException("","cannot save class credit");
        }
        return classCreditDTO;
    }

    private Semester getSemester(ClassCreditDTO classCreditDTO) {
        Semester semester = semesterRepository.findFirstByYearAndNum(classCreditDTO.getYear(), classCreditDTO.getSemesterNo());
        // create new semester if null
        if (Objects.isNull(semester)) {
            semester = semesterRepository.save(Semester.builder()
                    .year(classCreditDTO.getYear())
                    .num(classCreditDTO.getSemesterNo())
                    .name(classCreditDTO.getYear() + " - " + classCreditDTO.getSemesterNo())
                    .build());
        }
        return semester;
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
        return MaintenanceMapper.INSTANCE.toStudentDTOs(entities.getContent());
    }

    @Override
    public LecturerDTO getLecture(Long id) {
        return MaintenanceMapper.INSTANCE.toLectureDTO(lecturerRepository.findById(id).orElse(null));
    }

    @Override
    public List<LecturerDTO> getLectures(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Lecturer> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Lecturer> entities = lecturerRepository.findAll(specification, pageable);
        return MaintenanceMapper.INSTANCE.toLectureDTO(entities.getContent());
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
        List<SubjectDTO> res = entities.map(MaintenanceMapper.INSTANCE::toSubjectDTO).stream().collect(Collectors.toList());
        res.stream().filter(r -> Objects.nonNull(r.getPrerequisite())).forEach(x -> {
            x.setPrerequisiteCode(x.getPrerequisite().getSubjectCode());
        });
        res.forEach(this::buildPhanMonInfo);
        return res;
    }

    private void buildPhanMonInfo(SubjectDTO x) {
        List<Long> gvs = new ArrayList<>();
        List<String> gvStr = new ArrayList<>();
        if (!CollectionUtils.isEmpty(x.getLecturers())) {
            x.getLecturers().forEach(lecturerDTO -> {
                gvs.add(lecturerDTO.getLecturerId());
                gvStr.add(lecturerDTO.getProfile().getFullName());
            });
        }

        x.setListGV(gvs);
        x.setListGVStr(gvStr.toString().replace("[","").replace("]",""));
    }

    @Override
    public List<ClassroomDTO> getClassroom(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Classroom> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Classroom> entities = classroomRepository.findAll(specification, pageable);
        return entities.map(MaintenanceMapper.INSTANCE::toClassroomDTO).stream().collect(Collectors.toList());
    }

    @Override
    public List<ClassCreditDTO> classCreditRetrieve(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<ClassCredit> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<ClassCredit> entities = classCreditRepository.findAll(specification, pageable);
        List<ClassCreditDTO> res = entities.map(MaintenanceMapper.INSTANCE::toClassCreditDTO).stream().collect(Collectors.toList());
        res.forEach(x -> {
            if (Objects.nonNull(x.getSemester())) {
                x.setYear(x.getSemester().getYear());
                x.setSemesterNo(x.getSemester().getNum());
            }
            x.setShowDetails(x.getClassCreditId() + "-" +  x.getSubject().getName() + "-" + x.getYear() + "-" + x.getSemesterNo());
        });
        return res;
    }

    @Override
    public List<FacultyDTO> facultyRetrieve(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Faculty> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Faculty> entities = facultyRepository.findAll(specification, pageable);
        return entities.map(MaintenanceMapper.INSTANCE::toFacultyDTO).stream().collect(Collectors.toList());
    }

    @Override
    public ClassCreditDTO updateClassCredit(ClassCreditDTO classCreditDTO) {
        ClassCredit classCredit = classCreditRepository.findById(classCreditDTO.getClassCreditId()).orElse(null);
        if (Objects.nonNull(classCredit)) {
            classCredit.setRegisOpening(classCreditDTO.getRegisOpening());
            classCredit.setRegisClosing(classCreditDTO.getRegisClosing());
            classCredit.setSemester(getSemester(classCreditDTO));
            classCredit.setStatus(classCreditDTO.getStatus());
            classCreditRepository.save(classCredit);
        }
        return MaintenanceMapper.INSTANCE.toClassCreditDTO(classCredit);
    }

    @Override
    public Object submitTimeTable(SubmitTimeTableReq req) {
        List<TimeTable> timeTables = new ArrayList<>();
        List<LocalDateTime> weekDays = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            weekDays.add(LocalDateTime.of(2023,12, 11 + i,0,0,0));
        }
        Schedule schedule = null;
        if (Objects.nonNull(req)) {
            List<geneticalgorithm.Classroom> classrooms = new ArrayList<>();
            List<Timeslot> timeslots = new ArrayList<>();
            List<Professor> professors = new ArrayList<>();
            List<Course> courses = new ArrayList<>();
            List<Studentgroup> studentGroups = new ArrayList<>();
            // set group
            if (Objects.nonNull(req.getListClass())) {
                List<Class> listStudentClass = classRepository.findAllByClassIdIn(req.getListClass());
                listStudentClass.forEach(cl -> {
                    int classSize = Objects.nonNull(cl.getStudents()) ? cl.getStudents().size() : 0;
                    studentGroups.add(new Studentgroup(Math.toIntExact(cl.getClassId()), classSize, req.getListSubjects().stream().mapToInt(aa -> Math.toIntExact(aa)).toArray()));
                });
            }
            // set room
            if (CollectionUtils.isEmpty(req.getListRooms())) {
                List<Classroom> classroomList = classroomRepository.findAll();
                classroomList.forEach(cr -> {
                    classrooms.add(new geneticalgorithm.Classroom(Math.toIntExact(cr.getClassroomId()), cr.getName(), cr.getMaxSize()));
                });
            } else {
                List<Classroom> classroomList = classroomRepository.findAllByClassroomIdIn(req.getListRooms());
                classroomList.forEach(cr -> {
                    classrooms.add(new geneticalgorithm.Classroom(Math.toIntExact(cr.getClassroomId()), cr.getName(), cr.getMaxSize()));
                });
            }
            // set Time slot

            // set professors & course
            req.getListSubjects().forEach(x -> {
                ClassCredit classCredit = classCreditRepository.findById(x).orElse(null);
                if (Objects.isNull(classCredit)) {
                    throw new RuntimeException("Cannot find LTC!1");
                }
                classCredit.getSubject().getLecturers().forEach(lec -> {
                    if (professors.stream().noneMatch(pro -> (pro.getProfessorId() == lec.getLecturerId()))) {
                        Professor professor = new Professor(Math.toIntExact(lec.getLecturerId()),lec.getProfile().getFullName());
                        professors.add(professor);
                    }
                });
                int[] lecturerIds = classCredit.getSubject().getLecturers().stream().mapToInt(e -> (int) Integer.valueOf(e.getLecturerId().toString())).toArray();
                Course course = new Course(
                        Integer.valueOf(classCredit.getClassCreditId().toString()),
                        classCredit.getSubject().getSubjectCode(),
                        classCredit.getSubject().getName(),
                        lecturerIds
                );
                courses.add(course);
            });
            try {
                schedule = buildSchedule(initializeSchedule(classrooms,
                        timeslots,
                        professors,
                        courses,
                        studentGroups));
            } catch (BusinessException ex) {
                throw new RuntimeException(ex.getMessage());
            }

        } else {
            schedule = buildSchedule(initializeSchedule());
        }
        if (schedule.getClasses() == null || schedule.getClasses().length == 0) {
            throw new RuntimeException("Cannot create TimeTable using Generic Algorithm!!");
        }
        geneticalgorithm.Class classes[] = schedule.getClasses();
        int classIndex = 1;
        for (geneticalgorithm.Class bestClass : classes) {
            Long ltc = (long) bestClass.getCourseId();
            Long stdClass = (long) bestClass.getGroupId();
            Long roomId = (long) bestClass.getRoomId();
            Long lectureId = (long) bestClass.getProfessorId();
            int dayIndex = bestClass.getTimeslotId() / 4;
            if (dayIndex == 5) {
                dayIndex = 0;
            }
            Long systemShiftId = (long) bestClass.getTimeslotId() % 4;
            ClassCredit classCredit = classCreditRepository.findById(ltc).orElse(null);
            Lecturer lecturer = lecturerRepository.findById(lectureId).orElse(null);
            Classroom classroom = classroomRepository.findById(roomId).orElse(null);
            ShiftSystem shiftSystem = shiftSystemRepository.findById(systemShiftId + 1).orElse(new ShiftSystem());

            classCredit.setLecturer(lecturer);
            classCredit.setClassrooms(List.of(classroom));
            // todo filter slot by day and group it
            TimeTable timeTable = TimeTable.builder()
                    .status(Boolean.TRUE)
                    .classCredit(classCredit)
                    .classroom(classroom)
                    .lessonDate(weekDays.get(dayIndex).withHour(shiftSystem.getTimeStart().getHour()).withMinute(shiftSystem.getTimeStart().getMinute()))
                    .shiftSystems(List.of(shiftSystem))
                    .build();
            timeTables.add(timeTable);
            classIndex++;
        }
        List<TimeTableDTO> res = MaintenanceMapper.INSTANCE.toTimeTableDTO(timeTables);
        for (int i = 0; i < res.size(); i++) {
            TimeTableDTO dto = res.get(i);
            dto.setId((long) i + 1);
            dto.setTitle(dto.getClassCredit().getSubject().getName() +
                    "\n" + "Phòng: " + dto.getClassroom().getName() +
                    "\n" + "GV: " +  dto.getClassCredit().getLecturer().getProfile().getFullName());
            dto.setDescription(dto.getTitle());
            dto.setStart(dto.getLessonDate());
            dto.setEnd(dto.getLessonDate().withHour(dto.getShiftSystems().get(0).getTimeClose().getHour()).withMinute(dto.getShiftSystems().get(0).getTimeClose().getMinute()));
        }
        return res;
    }

    @Override
    public List<StudentClassDTO> studentClassRetrieve(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Class> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Class> entities = classRepository.findAll(specification, pageable);
        List<StudentClassDTO> res = entities.map(MaintenanceMapper.INSTANCE::toStudentClassDTO).stream().collect(Collectors.toList());
        return res;
    }

    @Override
    public List<TimeTableDTO> saveTimetable(SaveTimetableReq req) {
        List<TimeTable> timeTables = MaintenanceMapper.INSTANCE.toTimeTable(req.getTimeTables());
        if (!CollectionUtils.isEmpty(timeTables)) {
            try {
                timeTables.forEach(x -> {
                    ClassCredit classCredit = classCreditRepository.findById(x.getClassCredit().getClassCreditId()).orElse(null);
                    classCredit.setDateStart(req.getStartDate());
                    classCreditRepository.save(classCredit);
                    // todo validate room

                    // todo validate lecturer

                    // todo validate student class

                    // todo validate no of week

                });
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi validate field \n" + e.getMessage());
            }

        }
        try {
            timeTableRepository.saveAll(timeTables);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu TKB\n" + e.getMessage());
        }
        return MaintenanceMapper.INSTANCE.toTimeTableDTO(timeTables);
    }

    @Override
    public List<TimeTableDTO> retrieveTimetable(SearchRequest request) {
        if (Objects.isNull(request)) {
            request = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<TimeTable> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<TimeTable> entities = timeTableRepository.findAll(specification, pageable);
        List<TimeTableDTO> res = entities.map(MaintenanceMapper.INSTANCE::toTimeTableDTO).stream().collect(Collectors.toList());
        res.forEach(dto -> {
            dto.setTitle(dto.getClassCredit().getSubject().getName() +
                    "\n" + "Phòng: " + dto.getClassroom().getName() +
                    "\n" + "GV: " +  dto.getClassCredit().getLecturer().getProfile().getFullName());
            dto.setDescription(dto.getTitle());
            dto.setStart(dto.getLessonDate());
            dto.setEnd(dto.getLessonDate().withHour(dto.getShiftSystems().get(0).getTimeClose().getHour()).withMinute(dto.getShiftSystems().get(0).getTimeClose().getMinute()));
        });
        return res;
    }

    private Subject buildRreRequisiteSubject(String subjectCode) {
        Subject subject = subjectRepository.findFirstBySubjectCode(subjectCode);
        if (Objects.isNull(subject)) {
            throw new RuntimeException("Subject " + subjectCode + " NOT EXITS");
        }
        return subject;
    }

}
