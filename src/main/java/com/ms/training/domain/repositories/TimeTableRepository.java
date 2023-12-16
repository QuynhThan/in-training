package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.ClassCredit;
import com.ms.training.domain.entities.training.Classroom;
import com.ms.training.domain.entities.training.Lecturer;
import com.ms.training.domain.entities.training.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, JpaSpecificationExecutor<TimeTable> {
    TimeTable findFirstByLessonDateInAndClassroomAndStatus(List<LocalDateTime> dates, Classroom classroom, Boolean status);
    TimeTable findFirstByLessonDateInAndClassCredit_LecturerAndStatus(List<LocalDateTime> dates, Lecturer lecturer, Boolean status);
    TimeTable findFirstByLessonDateInAndClassCredit_StudentClassIdAndStatus(List<LocalDateTime> dates, Long studentClassId, Boolean status);
    TimeTable findFirstByLessonDateInAndClassCredit(List<LocalDateTime> dates, ClassCredit classCredit);
    List<TimeTable> findByClassCredit(ClassCredit classCredit);

}
