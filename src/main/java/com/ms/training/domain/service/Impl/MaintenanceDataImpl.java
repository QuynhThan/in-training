package com.ms.training.domain.service.Impl;

import com.ms.training.application.dto.request.SaveTimetableReq;
import com.ms.training.application.dto.request.SubmitSubjectRequest;
import com.ms.training.application.dto.search.FilterRequest;
import com.ms.training.domain.enums.FieldType;
import com.ms.training.domain.enums.Operator;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
    ClassCreditsStudentsRepository classCreditsStudentsRepository;

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

    @Autowired
    CurriculumRepository curriculumRepository;
    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        if (subjectDTO.isPhanMon()) {
            Subject mySubject = subjectRepository.findFirstBySubjectCode(subjectDTO.getSubjectCode());
            if (Objects.isNull(mySubject)) {
                throw new RuntimeException("Invalid Subject!!");
            }

//            List<Lecturer> lecturers = lecturerRepository.findByLecturerIdIn(subjectDTO.getListGV());
//            mySubject.setLecturers(lecturers);
            try {
                subjectRepository.save(mySubject);
            } catch (Exception exception) {
                throw new RuntimeException("Cannot save subject!!");
            }
            return MaintenanceMapper.INSTANCE.toSubjectDTO(mySubject);
        }
        // 1 tins chir = 15 tietes
        int totalTiet = subjectDTO.getCreditNum() * 15;
        int totalInput = subjectDTO.getTheoryNum() + subjectDTO.getPracticalNum();
        if (totalInput != totalTiet) {
            throw new RuntimeException("Số tiết không hợp lệ!!!\n1 tín chỉ = 15 tiết");
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
            throw new RuntimeException("Cannot save Subject!");
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
            throw new RuntimeException("Khong the xoa giang vien");
        }
        return lecturerDTO;
    }

    @Override
    @Transactional
    public LecturerDTO addLecturers(LecturerDTO lecturerDTO) {
        Lecturer lecturer = MaintenanceMapper.INSTANCE.toLecturers(lecturerDTO);
        if (lecturerDTO.isUpdate()) {
            if (Strings.isEmpty(lecturerDTO.getFullName()) || Strings.isEmpty(lecturerDTO.getPhone())) {
                throw new RuntimeException("Tên và sdt rỗng!!");
            }
            lecturer = lecturerRepository.findById(lecturerDTO.getLecturerId()).orElse(null);
            lecturer.getProfile().setFullName(lecturerDTO.getFullName());
            lecturer.getProfile().setPhone(lecturerDTO.getPhone());
            profileRepository.saveAndFlush(lecturer.getProfile());
            return MaintenanceMapper.INSTANCE.toLectureDTO(lecturer);
        }
        try {
            if (Objects.nonNull(accountRepository.findByUsername(lecturerDTO.getAccount().getUsername()))) {
                throw new RuntimeException("Ten tài khoản đã tồn tại!!");
            }
            if (Objects.nonNull(profileRepository.findFirstByProfileCode(lecturerDTO.getProfile().getProfileCode()))) {
                throw new RuntimeException("Mã người dùng đã tồn tai!!");
            }
            accountRepository.saveAndFlush(lecturer.getAccount());
            profileRepository.saveAndFlush(lecturer.getProfile());
            lecturerRepository.saveAndFlush(lecturer);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("cannot save lecturers");
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
            throw new RuntimeException("cannot save classroom");
        }
        return classroomDTO;
    }

    @Override
    public ClassroomDTO deleteClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = MaintenanceMapper.INSTANCE.toClassroom(classroomDTO);
        try {
            classroomRepository.delete(classroom);
        } catch (Exception e) {
            throw new RuntimeException("cannot delete classroom");
        }
        return classroomDTO;
    }

    @Override
    @Transactional
    public ClassCreditDTO addClassCredit(ClassCreditDTO classCreditDTO) {
        validateClassCreditReq(classCreditDTO, true);
        ClassCredit classCredit = MaintenanceMapper.INSTANCE.toClassCredit(classCreditDTO);
        Subject subject = subjectRepository.findById(classCreditDTO.getSubjectId()).orElse(null);
        Lecturer lecturer = lecturerRepository.findById(classCreditDTO.getLecturerId()).orElse(null);
        if (Objects.isNull(classCreditDTO.getRoomId())) {
            classCreditDTO.setRoomId(0l);
        }
        Classroom classroom = classroomRepository.findById(classCreditDTO.getRoomId()).orElse(null);
        Semester semester = getSemester(classCreditDTO);
        List<Class> studentClasses = classRepository.findAllByClassIdIn(classCreditDTO.getListClass());
        Class aClass = classRepository.findById(classCreditDTO.getLopHoc()).orElse(null);
        if (Objects.nonNull(aClass)) {
            studentClasses.add(aClass);
        }
        if (Objects.isNull(subject) || CollectionUtils.isEmpty(studentClasses)) {
            throw new RuntimeException("Subject or Student class not found!!");
        }
//        classCredit.setMaxSize(classroom.getMaxSize());
        classCredit.setLecturer(lecturer);
        classCredit.setSubject(subject);
        classCredit.setStatus(StatusEnum.INACTIVE.name());
        classCredit.setSemester(semester);
        if (Objects.nonNull(classroom)) {
            classCredit.setClassrooms(List.of(classroom));
        }
        // validate class
        studentClasses.forEach(c -> {
            Curriculum curriculum = curriculumRepository.findFirstByFaculty(c.getFaculty());
            if (Objects.isNull(curriculum)) {
                throw new RuntimeException("Khoa " + c.getFaculty().getName() + " chưa có chương trình đạo tạo nên không thể tạo LTC cho lớp "+ c.getName());
            }
            if (!curriculum.getSubjects().contains(subject)) {
                throw new RuntimeException("Môn " + subject.getName() + " không có trong chương trình đào tạo của lớp " + c.getName());
            }
        });
        try {
            studentClasses.forEach(stuClass -> {
                classCredit.setStudentClassId(stuClass.getClassId());
                classCreditRepository.save(classCredit);
            });
        } catch (Exception e) {
            throw new RuntimeException("cannot save class credit");
        }
        return classCreditDTO;
    }

    private void validateClassCreditReq(ClassCreditDTO classCreditDTO, boolean isAdd) {
        if (Objects.nonNull(classCreditDTO.getRegisOpening()) && classCreditDTO.getRegisOpening().after(classCreditDTO.getRegisClosing())) {
            throw new RuntimeException("Ngày mở phải trước hạn");
        }
        // check input year
        int thisYear = LocalDateTime.now().getYear();
        if (classCreditDTO.getSemesterNo() != 1 && classCreditDTO.getSemesterNo() != 2) {
            throw new RuntimeException("Học kỳ không hợp lệ!!");
        }
        if (classCreditDTO.getYear() < (thisYear - 1)) {
            throw new RuntimeException("Năm học không hợp lệ!!");
        }
        if (classCreditDTO.getYear() == (thisYear - 1) && classCreditDTO.getSemesterNo() == 1) {
            throw new RuntimeException("Năm học không hợp lệ!!");
        }
        // check class and group
        if (isAdd) {
            Semester semester = getSemester(classCreditDTO);
            Subject subject = subjectRepository.findById(classCreditDTO.getSubjectId()).orElse(null);
            Class stuClass = classRepository.findById(classCreditDTO.getLopHoc()).orElse(null);
            ClassCredit classCredit = classCreditRepository.findFirstByStudentClassIdAndGroupNumberAndSemesterAndSubject(classCreditDTO.getLopHoc(), classCreditDTO.getGroupNumber(), semester, subject);

            if (Objects.nonNull(classCredit)) {
                throw new RuntimeException("Lớp " + stuClass.getName() + " Nhóm " + classCredit.getGroupNumber() + "\nĐã tồn tại trong hoc ky!!");
            }
        }

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
            throw new RuntimeException("cannot delete class credit");
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
        List<ClassCredit> results = new ArrayList<>();
        Semester semester = null;
        if(Objects.nonNull(request.getSemesterId())) {
            semester = semesterRepository.findById(request.getSemesterId()).orElse(null);
        }
        if (Objects.nonNull(request.getUserName()) && request.getRole().equalsIgnoreCase("student")) {
            Student student = studentRepository.findStudentByAccount_Username(request.getUserName());
            if(Objects.isNull(student)) {
                throw new RuntimeException("Vui lòng F5 hoặc đăng nhập lại!!");
            }
            Curriculum stuCurri = curriculumRepository.findFirstByFaculty(student.getAClass().getFaculty());
            for(ClassCredit classCredit : entities.getContent()) {
                if (!classCredit.getSubject().getCurriculums().contains(stuCurri)) {
                    continue;
                }
                ClassCreditsStudents dkm = classCreditsStudentsRepository.findFirstByClassCreditAndStudent(classCredit, student);
                Date today = new Date();
                if (request.isRegistered() && Objects.nonNull(dkm) && dkm.getStatus().equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
                    if (today.compareTo(classCredit.getRegisOpening()) > 0 && today.compareTo(classCredit.getRegisClosing()) <= 0) {
                        results.add(classCredit);
                    }

                } else if (!request.isRegistered() && (Objects.isNull(dkm) || dkm.getStatus().equalsIgnoreCase(StatusEnum.INACTIVE.name()))) {
                    if (today.compareTo(classCredit.getRegisOpening()) > 0 && today.compareTo(classCredit.getRegisClosing()) <= 0) {
                        results.add(classCredit);
                    }
                }
            }
        } else if(Objects.nonNull(semester)) {
            for (ClassCredit classCredit : entities.getContent()) {
                if (classCredit.getSemester().getSemesterId() == semester.getSemesterId() && classCredit.getStatus().equalsIgnoreCase(StatusEnum.INACTIVE.name())) {
                    results.add(classCredit);
                }
            }

        }else {
            results.addAll(entities.getContent());
        }

        List<ClassCreditDTO> res = MaintenanceMapper.INSTANCE.toClassCreditDTOs(results);
        res.forEach(x -> {
            if (Objects.nonNull(x.getSemester())) {
                x.setYear(x.getSemester().getYear());
                x.setSemesterNo(x.getSemester().getNum());
//                x.getSemester().setName(buildNameSemester());
            }
            x.setListGV(new ArrayList<>());
            if (!CollectionUtils.isEmpty(x.getLecturers())) {
                x.getLecturers().forEach(l -> {
                    x.getListGV().add(l.getLecturerId());
                });
            }
            Class myClass = classRepository.findById(x.getStudentClassId()).orElse(null);
            String className = Objects.nonNull(myClass) ? myClass.getName() : "";
            x.setClassName(className);
            x.setShowDetails(x.getClassCreditId() + "-" +  x.getSubject().getName() +"-"+ className + "-" + x.getYear() + "-" + x.getSemesterNo());
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
        StatusEnum statusEnum = null;
        try {
            statusEnum = StatusEnum.valueOf(classCreditDTO.getStatus());
            validateClassCreditReq(classCreditDTO, false);
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception e) {
            throw new RuntimeException("Status không hợp lệ!!!");
        }
        ClassCredit classCredit = classCreditRepository.findById(classCreditDTO.getClassCreditId()).orElse(null);
        if (statusEnum.name().equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
            if (Objects.isNull(classCredit.getLecturer())) {
                throw new RuntimeException("Chưa được giao giảng viên vui lòng chuyển sang INACTIVE và tạo lại lịch học!!");
            }
        }
        if (statusEnum.name().equalsIgnoreCase(StatusEnum.CANCELLED.name()) || statusEnum.name().equalsIgnoreCase(StatusEnum.INACTIVE.name())) {
            List<TimeTable> timeTables = timeTableRepository.findByClassCredit(classCredit);
            timeTables.forEach(x -> {
                x.setStatus(Boolean.FALSE);
            });
            timeTableRepository.saveAll(timeTables);
        }
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(req.getStartDate());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        buildListDay(weekDays, dayOfWeek, req.getStartDate());
        if (Objects.nonNull(req.getSemesterId())) {
            handleSubmitTimeTableBySemester(req, true);
        }
        Schedule schedule = null;
        if (Objects.nonNull(req)) {
            List<geneticalgorithm.Classroom> classrooms = new ArrayList<>();
            List<Timeslot> timeslots = new ArrayList<>();
            List<Professor> professors = new ArrayList<>();
            List<Course> courses = new ArrayList<>();
            List<Studentgroup> studentGroups = new ArrayList<>();
            List<Long> classIds = new ArrayList<>();

            // set professors & course
            req.getListSubjects().forEach(x -> {
                ClassCredit classCredit = classCreditRepository.findById(x).orElse(null);
                if (Objects.isNull(classCredit)) {
                    throw new RuntimeException("Cannot find LTC!");
                }
                // set group
                // add 1 LTC to 1 LOP
                Class cl = classRepository.findById(classCredit.getStudentClassId()).orElse(null);
                int classSize = Objects.nonNull(cl.getStudents()) ? cl.getStudents().size() : 0;
                studentGroups.add(new Studentgroup(Math.toIntExact(cl.getClassId() + classCredit.getClassCreditId()), classSize, new int[]{Math.toIntExact(x)}));
                //

                classIds.add(classCredit.getStudentClassId());
                classCredit.getLecturers().forEach(lec -> {
                    // TODO CREATE TKB FOR CHECK 2 GVs
                    if (professors.stream().noneMatch(pro -> (pro.getProfessorId() == lec.getLecturerId()))) {
                        Professor professor = new Professor(Math.toIntExact(lec.getLecturerId()),lec.getProfile().getFullName());
                        professors.add(professor);
                    }
                });
                int[] lecturerIds = classCredit.getLecturers().stream().mapToInt(e -> (int) Integer.valueOf(e.getLecturerId().toString())).toArray();
                Course course = new Course(
                        Integer.valueOf(classCredit.getClassCreditId().toString()),
                        classCredit.getSubject().getSubjectCode(),
                        classCredit.getSubject().getName(),
                        lecturerIds
                );
                courses.add(course);
            });
            req.setListClass(classIds);
            // set group
//            if (Objects.nonNull(req.getListClass())) {
//                List<Class> listStudentClass = classRepository.findAllByClassIdIn(req.getListClass());
//                listStudentClass.forEach(cl -> {
//                    int classSize = Objects.nonNull(cl.getStudents()) ? cl.getStudents().size() : 0;
//                    studentGroups.add(new Studentgroup(Math.toIntExact(cl.getClassId()), classSize, req.getListSubjects().stream().mapToInt(aa -> Math.toIntExact(aa)).toArray()));
//                });
//            }
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

            //
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
            Long stdClass = (long) bestClass.getGroupId() - ltc;
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
            LocalDateTime dateTime = weekDays.get(dayIndex);
            int noOfWeeks = classCredit.getSubject().getTheoryNum() + classCredit.getSubject().getPracticalNum();
            noOfWeeks = getNoOfWeeks(noOfWeeks, false);
            for (int i = 0; i < noOfWeeks; i++) {
                buildTimeTable(classCredit, classroom, dateTime, i, shiftSystem, timeTables);
            }
            classIndex++;
        }
        List<TimeTableDTO> res = MaintenanceMapper.INSTANCE.toTimeTableDTO(timeTables);
        for (int i = 0; i < res.size(); i++) {
            TimeTableDTO dto = res.get(i);
            Class stuClass = classRepository.findById(dto.getClassCredit().getStudentClassId()).orElse(null);
            String className = Objects.nonNull(stuClass) ? stuClass.getName() : "";
            dto.setIndex(i);
            dto.setId((long) i + 1);
            dto.setTitle(dto.getClassCredit().getSubject().getName() +
                    "\n" + "Phòng: " + dto.getClassroom().getName() +
                    "\n" + "GV: " +  dto.getClassCredit().getLecturer().getProfile().getFullName() +
                    "\n" + "Lớp: " + className +
                    "\n" + "Nhóm: " + dto.getClassCredit().getGroupNumber());
            dto.setDescription(dto.getTitle());
            dto.setStart(dto.getLessonDate());
            dto.setEnd(dto.getLessonDate().withHour(dto.getShiftSystems().get(0).getTimeClose().getHour()).withMinute(dto.getShiftSystems().get(0).getTimeClose().getMinute()));
        }
        return res;
    }

    private void handleSubmitTimeTableBySemester(SubmitTimeTableReq req, boolean isSave) {
        Semester semester = semesterRepository.findById(req.getSemesterId()).orElse(null);
        if (Objects.nonNull(semester)) {
//            req.getRegisOpening().setTime(); // start of day
//            req.getRegisClosing().setTime(); // end of day
            semester.setDateStart(req.getStartDate());
            semester.setRegisClosing(req.getRegisClosing());
            semester.setRegisOpening(req.getRegisOpening());
            semesterRepository.save(semester);
        }
        List<ClassCredit> classCredits = classCreditRepository.findAllBySemesterAndStatus(semester, StatusEnum.INACTIVE.name());
        req.setListSubjects(new ArrayList<>());
        classCredits.forEach(x -> {
            req.getListSubjects().add(x.getClassCreditId());
            x.setRegisClosing(req.getRegisClosing());
            x.setRegisOpening(req.getRegisOpening());
            x.setDateStart(req.getStartDate());
        });
        if (isSave) {
            classCreditRepository.saveAll(classCredits);
        }
    }

    private static void buildTimeTable(ClassCredit classCredit, Classroom classroom, LocalDateTime dateTime, int i, ShiftSystem shiftSystem, List<TimeTable> timeTables) {
        TimeTable timeTable = TimeTable.builder()
                .status(Boolean.TRUE)
                .classCredit(classCredit)
                .classroom(classroom)
                .lessonDate(dateTime.plusDays(i * 7).withHour(shiftSystem.getTimeStart().getHour()).withMinute(shiftSystem.getTimeStart().getMinute()))
                .shiftSystems(List.of(shiftSystem))
                .build();
        timeTables.add(timeTable);
    }

    private static int getNoOfWeeks(int noOfWeeks, boolean isSave) {
        if (!isSave)
            return 1;
        return noOfWeeks % 2 == 0 ? noOfWeeks / 2 : (noOfWeeks / 2) + 1;
    }

    private void buildListDay(List<LocalDateTime> weekDays, int dayOfWeek, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        switch (dayOfWeek) {
            case 1:
                for (int i = 0; i< 5; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + 1 + i,0,0,0));
                }
                break;
            case 2:
                for (int i = 0; i< 5; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + i,0,0,0));
                }
                break;
            case 3:
                for (int i = 0; i< 4; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + i,0,0,0));
                }
                weekDays.add(LocalDateTime.of(year,month, day + 6,0,0,0));
                break;
            case 4:
                for (int i = 0; i< 3; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + i,0,0,0));
                }
                weekDays.add(LocalDateTime.of(year,month, day + 5,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 6,0,0,0));
                break;
            case 5:
                for (int i = 0; i< 2; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + i,0,0,0));
                }
                weekDays.add(LocalDateTime.of(year,month, day + 4,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 5,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 6,0,0,0));
                break;
            case 6:
                for (int i = 0; i< 1; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + i,0,0,0));
                }
                weekDays.add(LocalDateTime.of(year,month, day + 3,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 4,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 5,0,0,0));
                weekDays.add(LocalDateTime.of(year,month, day + 6,0,0,0));
                break;
            case 7:
                for (int i = 0; i< 5; i++) {
                    weekDays.add(LocalDateTime.of(year,month, day + 2 + i,0,0,0));
                }
                break;
        }
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
    @Transactional
    public List<TimeTableDTO> saveTimetable(SaveTimetableReq req) {
        List<ShiftSystem> shiftSystems = shiftSystemRepository.findAll();
        for(int i = 0; i < req.getTimeTables().size(); i++) {
            req.getTimeTables().get(i).setEnd(req.getEndDates().get(i).minusHours(7));
            req.getTimeTables().get(i).setStart(req.getStartDates().get(i).minusHours(7));
            req.getTimeTables().get(i).setLessonDate(req.getStartDates().get(i).minusHours(7));
            int finalI = i;
            AtomicBoolean validTime = new AtomicBoolean(false);
            List<ShiftSystem> myKip = new ArrayList<>();
            shiftSystems.forEach(x -> {
                if (x.getTimeStart().toLocalTime().equals(req.getTimeTables().get(finalI).getLessonDate().toLocalTime())) {
                    myKip.add(x);
                    validTime.set(true);
                }
            });
            req.getTimeTables().get(finalI).setShiftSystems(MaintenanceMapper.INSTANCE.toShiftSystemDTOS(myKip));
            if (!validTime.get()) {
                throw new RuntimeException("Thời gian không hợp lệ cho \n"+req.getTimeTables().get(i).getDescription());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(req.getStartDate());
            int dayOfW = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfW == 1 || dayOfW == 7) {
                throw new RuntimeException("Không có kíp học cho thứ 7, chủ nhật\nThời gian không hợp lệ cho \n"+req.getTimeTables().get(i).getDescription());
            }
        }
        // todo save SO TIET VAOF DATABASE
        List<TimeTable> timeTables = MaintenanceMapper.INSTANCE.toTimeTable(req.getTimeTables());
        if (!CollectionUtils.isEmpty(timeTables)) {
            try {
                List<TimeTable> added = new ArrayList<>();
                for (TimeTable timeTable1 : timeTables) {
                    ClassCredit ccTmp = classCreditRepository.findById(timeTable1.getClassCredit().getClassCreditId()).orElse(null);
                    int noOfWeeks = ccTmp.getSubject().getTheoryNum() + ccTmp.getSubject().getPracticalNum();
                    noOfWeeks = getNoOfWeeks(noOfWeeks, true) - 1;
                    for (int i = 1; i <= noOfWeeks; i++) {
                        TimeTable timeTable2 = TimeTable.builder()
                                .status(timeTable1.getStatus())
                                .shiftSystems(timeTable1.getShiftSystems())
                                .lessonDate(timeTable1.getLessonDate().plusDays(i*7l))
                                .classroom(timeTable1.getClassroom())
                                .classCredit(timeTable1.getClassCredit())
                                .build();
                        added.add(timeTable2);
                    }
                }
                timeTables.addAll(added);

                timeTables.forEach(x -> {
                    TimeTable timeTable = null;
                    List<LocalDateTime> dates = List.of(x.getLessonDate());
                    // todo validate room
                    timeTable = timeTableRepository.findFirstByLessonDateInAndClassroomAndStatus(dates, x.getClassroom(), true);
                    if (Objects.nonNull(timeTable)) {
                        throw new RuntimeException(timeTable.getLessonDate() + "\nPhòng học đã được sử dụng trong kip này!!\nLTC:" + timeTable.getClassCredit().getSubject().getName()
                                + "\nID: " + timeTable.getClassCredit().getClassCreditId());
                    }
                    // todo validate lecturer
                    timeTable = timeTableRepository.findFirstByLessonDateInAndClassCredit_LecturerAndStatus(dates, x.getClassCredit().getLecturer(), true);
                    if (Objects.nonNull(timeTable)) {
                        throw new RuntimeException(timeTable.getLessonDate() + "\nGiảng viên đã có tiết trong kip này!!\nLTC:" + timeTable.getClassCredit().getSubject().getName()
                                + "\nID: " + timeTable.getClassCredit().getClassCreditId());
                    }
                    // todo validate student class
//                    List<TimeTableDTO> search = this.
//                    timeTable = timeTableRepository.findFirstByLessonDateInAndClassCredit_StudentClassIdAndStatus(dates, x.getClassCredit().getStudentClassId(), true);
//                    if (Objects.nonNull(timeTable)) {
//                        throw new RuntimeException(timeTable.getLessonDate() + "\nLớp " + timeTable.getClassCredit().getStudentClassId() + " đã học trong kip này!!\nLTC:" + timeTable.getClassCredit().getSubject().getName()
//                                + "\nID: " + timeTable.getClassCredit().getClassCreditId());
//                    }
                    // todo validate no of week

                    //
                    Lecturer myLecture = lecturerRepository.findById(x.getClassCredit().getLecturer().getLecturerId()).orElse(null);
                    if (Objects.isNull(myLecture)) {
                        throw new RuntimeException("Không tìm thấy giảng viên " + x.getClassCredit().getLecturer().getLecturerId());
                    }
                    ClassCredit classCredit = classCreditRepository.findById(x.getClassCredit().getClassCreditId()).orElse(null);
                    classCredit.setDateStart(req.getStartDate());
                    classCredit.setMaxSize(x.getClassroom().getMaxSize());
                    classCredit.setStatus(StatusEnum.ACTIVE.name());
                    classCredit.setLecturer(myLecture);
                    classCreditRepository.save(classCredit);
                    x.setStatus(Boolean.TRUE);

                });
            } catch (Exception e) {
                throw new RuntimeException("Không thể tạo bởi vì \n" + e.getMessage());
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
        if (CollectionUtils.isEmpty(request.getFilters())) {
            request.setFilters(List.of(FilterRequest.builder()
                    .key("status")
                    .operator(Operator.EQUAL)
                    .fieldType(FieldType.BOOLEAN)
                    .value(Boolean.TRUE)
                    .build()));
        }
        SearchSpecification<TimeTable> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<TimeTable> entities = timeTableRepository.findAll(specification, pageable);
        List<TimeTable> timeTables = entities.toList();
        timeTables = timeTables.stream().filter(timeTable -> {
            boolean b = !timeTable.getClassCredit().getStatus().equalsIgnoreCase(StatusEnum.CANCELLED.name());
            return b;
        }).collect(Collectors.toList());
        List<TimeTable> filterTKB = new ArrayList<>();
        if (Objects.nonNull(request.getUserName()) && Objects.nonNull(request.getRole())) {
            switch (request.getRole()) {
                case "student":
                    Student student = studentRepository.findStudentByAccount_Username(request.getUserName());
                    if (Objects.isNull(student)) {
                        throw new RuntimeException("không tìm thấy sinh vien");
                    }
                    timeTables.forEach(x -> {
                        ClassCreditsStudents dkm = classCreditsStudentsRepository.findFirstByClassCreditAndStudent(x.getClassCredit(), student);
                        if (Objects.nonNull(dkm) && dkm.getStatus().equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
                            filterTKB.add(x);
                        }
                    });
                    break;
                case "lecturer":
                    Lecturer lecturer = lecturerRepository.findByAccount_Username(request.getUserName());
                    if (Objects.isNull(lecturer)) {
                        throw new RuntimeException("không tìm thấy giảng viên");
                    }
                    timeTables.forEach(x -> {
                        if (Objects.nonNull(x.getClassCredit().getLecturer()) && (x.getClassCredit().getLecturer().getLecturerId() == lecturer.getLecturerId())) {
                            filterTKB.add(x);
                        }
                    });
                    break;
                case "employee":
                    filterTKB.addAll(timeTables);
                    break;
            }
        }
        List<TimeTableDTO> res = MaintenanceMapper.INSTANCE.toTimeTableDTO(filterTKB);
        res.forEach(dto -> {
            Class stuClass = classRepository.findById(dto.getClassCredit().getStudentClassId()).orElse(null);
            String className = Objects.nonNull(stuClass) ? stuClass.getName() : "";
            dto.setTitle(dto.getClassCredit().getSubject().getName() +
                    "\n" + "Phòng: " + dto.getClassroom().getName() +
                    "\n" + "GV: " +  dto.getClassCredit().getLecturer().getProfile().getFullName() +
                    "\n" + "Lớp: " + className +
                    "\n" + "Nhóm: " + dto.getClassCredit().getGroupNumber());
            dto.setDescription(dto.getTitle());
            dto.setStart(dto.getLessonDate());
            dto.setEnd(dto.getLessonDate().withHour(dto.getShiftSystems().get(0).getTimeClose().getHour()).withMinute(dto.getShiftSystems().get(0).getTimeClose().getMinute()));
        });

        return res;
    }

    @Override
    public CurriculumDTO addCCDT(CurriculumDTO request) {
        List<Subject> subjects = subjectRepository.findSubjectsBySubjectIdIn(request.getSubjectIds());
        Faculty faculty = facultyRepository.findById(request.getFacultyId()).orElse(null);
        if (Objects.isNull(faculty) || CollectionUtils.isEmpty(subjects)) {
            throw new RuntimeException("Danh sach mon hoc rong, hoac khoa khong ton tai!!");
        }
        Curriculum exist = curriculumRepository.findFirstByFaculty(faculty);
        if (Objects.nonNull(exist) && Objects.nonNull(exist.getCurriculumId())) {
            request.setCurriculumId(exist.getCurriculumId());
            exist.getSubjects().forEach(x -> {
                if (!subjects.contains(x)) {
                    subjects.add(x);
                }
            });
        }
        Curriculum curriculum = new Curriculum();
        curriculum.setCurriculumId(request.getCurriculumId());
        curriculum.setName(request.getName());
        curriculum.setFaculty(faculty);
        curriculum.setSubjects(subjects);
        curriculum.setNumSubject(subjects.size());

        curriculumRepository.save(curriculum);
        return request;
    }

    @Override
    public Object registerSubmit(SubmitSubjectRequest request) {
        try {
            String saveStatus = request.getStatus() ? StatusEnum.ACTIVE.name() : StatusEnum.INACTIVE.name();
            ClassCredit classCredit = classCreditRepository.findById(request.getClassCredit().getClassCreditId()).orElse(null);
            Student student = studentRepository.findStudentByAccount_Username(request.getUserName());
            Subject thisSubject = classCredit.getSubject();
            List<ClassCredit> classCreditSubject = classCreditRepository.findAllBySubjectAndStatus(thisSubject, StatusEnum.ACTIVE.name());
            if (saveStatus.equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
                classCreditSubject.forEach(x -> {
                    ClassCreditsStudents dkm = classCreditsStudentsRepository.findFirstByClassCreditAndStudent(x, student);
                    if (Objects.nonNull(dkm) && dkm.getStatus().equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
                        if (x.getSemester().getYear() == classCredit.getSemester().getYear()) {
                            throw new RuntimeException("Môn " + thisSubject.getName() + " đã học trong năm " + classCredit.getSemester().getYear());
                        }
                    }
                });
            }
            if (Objects.isNull(student) || Objects.isNull(classCredit)) {
                throw new RuntimeException("");
            }
            if (saveStatus.equalsIgnoreCase(StatusEnum.ACTIVE.name())) {
                validateStudentClassCredit(student, classCredit);
            }
            ClassCreditsStudents dkm = classCreditsStudentsRepository.findFirstByClassCreditAndStudent(classCredit, student);
            if (Objects.nonNull(dkm)) {
                dkm.setStatus(saveStatus);
            } else {
                dkm = new ClassCreditsStudents();
                dkm.setStudent(student);
                dkm.setClassCredit(classCredit);
                dkm.setStatus(saveStatus);
            }
            classCreditsStudentsRepository.save(dkm);

        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi!!\nVui lòng thử lại.");
        }

        return request;
    }

    @Override
    @Transactional
    public Object triggerDKMonHoc() {
        List<ClassCredit> classCredits = classCreditRepository.findAllByStatus(StatusEnum.ACTIVE.name());
        classCredits.forEach(x -> {
            if (x.getStatus().equalsIgnoreCase(StatusEnum.ACTIVE.name()) && x.getRegisClosing().before(new Date())) {
                List<ClassCreditsStudents> dk = classCreditsStudentsRepository.findAllByClassCreditAndStatus(x, StatusEnum.ACTIVE.name());
                if (dk.size() >= x.getMinSize()) {
                    x.setStatus(StatusEnum.IN_PROGRESS.name());
                } else {
                    x.setStatus(StatusEnum.CANCELLED.name());
                }
                classCreditRepository.save(x);
            }

        });
        return null;
    }

    @Override
    public ClassCreditDTO phanMonGV(ClassCreditDTO classCreditDTO) {
        if (CollectionUtils.isEmpty(classCreditDTO.getListGV())) {
            throw new RuntimeException("Danh sach giang vien rong");
        }
        ClassCredit classCredit = classCreditRepository.findById(classCreditDTO.getClassCreditId()).orElse(null);
        List<Lecturer> lecturers = lecturerRepository.findByLecturerIdIn(classCreditDTO.getListGV());
        if (CollectionUtils.isEmpty(lecturers)) {
            throw new RuntimeException("Danh sach giang vien rong");
        }
        // TODO DUMMY set first lecturer for LTC
        classCredit.setLecturer(lecturers.get(0));
        classCredit.setLecturers(lecturers);
        classCreditRepository.save(classCredit);
        return classCreditDTO;
    }

    @Override
    public Object semesterRetrieve(SearchRequest req) {
        if (Objects.isNull(req)) {
            req = SearchRequest.builder()
                    .build();
        }
        SearchSpecification<Semester> specification = new SearchSpecification<>(req);
        Pageable pageable = SearchSpecification.getPageable(req.getPage(), req.getSize());
        Page<Semester> entities = semesterRepository.findAll(specification, pageable);
        List<Semester> semesters = entities.toList();
        semesters.forEach(x -> {
            x.setName(buildNameSemester(x.getYear(), x.getNum()));
        });
        List<SemesterDTO> res = MaintenanceMapper.INSTANCE.toSemesterDTOs(semesters);
        res.sort(Comparator.comparing(x -> x.getYear()));
        return res;
    }

    private static String buildNameSemester(Integer year, Integer num) {
        return "Năm học: " + year + " - " + (year + 1) + " -- Học kỳ: " + num;
    }

    private void validateStudentClassCredit(Student student, ClassCredit classCredit) {
        // check mon hoc tien quyet
        if (Objects.nonNull(classCredit.getSubject().getPrerequisite())) {
            List<ClassCredit> pps = classCreditRepository.findAllBySubject(classCredit.getSubject().getPrerequisite());
            ClassCreditsStudents dkm = classCreditsStudentsRepository.findFirstByStudentAndStatusAndClassCreditIn(student, StatusEnum.COMPLETED.name(), pps);
            if (Objects.isNull(dkm)) {
                throw new RuntimeException("Ban cần hoàn thành môn " + classCredit.getSubject().getPrerequisite().getName() + " trước!!");
            }
        }
        // check trùng lịch
        SearchRequest searchRequest = SearchRequest.builder()
                .userName(student.getAccount().getUsername())
                .role("student")
                .build();
        List<LocalDateTime> dateTimes = timeTableRepository.findByClassCreditAndStatus(classCredit, Boolean.TRUE).stream().map(TimeTable::getLessonDate).collect(Collectors.toList());
        List<TimeTableDTO> tkbs = retrieveTimetable(searchRequest);
        if (!CollectionUtils.isEmpty(tkbs)) {
            tkbs.forEach(x -> {
                if (dateTimes.contains(x.getLessonDate())) {
                    throw new RuntimeException("Bạn bị trùng lịch với môn "+ x.getClassCredit().getSubject().getName());
                }
            });
        }
        // check full slot
        if (classCreditsStudentsRepository.findAllByClassCredit(classCredit).size() >= classCredit.getMaxSize()) {
            throw new RuntimeException("Số lượng đã hết");
        }
    }

    private Subject buildRreRequisiteSubject(String subjectCode) {
        Subject subject = subjectRepository.findFirstBySubjectCode(subjectCode);
        if (Objects.isNull(subject)) {
            throw new RuntimeException("Subject " + subjectCode + " NOT EXITS");
        }
        return subject;
    }

}
