package com.ms.training.application.dto.training;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreditDTO {
    private Long classCreditId;
    private Integer groupNumber;
    private Integer minSize;
    private Integer maxSize;
    private Long roomId;
    private String status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regisOpening;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regisClosing;
    private Long subjectId;
    private Long studentClassId;
    private SubjectDTO subject;
    private Long semesterId;
    private Integer year;
    private int semesterNo;
    private SemesterDTO semester;
    private Long lecturerId;
    private LecturerDTO lecturer;
    private Long facultyId;
    private String facultyDisplay;
    private FacultyDTO faculty;
    private List<ExamDTO> examList;
//    private List<StudentDTO> students;
    private List<ClassCreditGroupDTO> groups;
    private String showDetails;
    private List<Long> listClass;
    private Long lopHoc;
    @JsonProperty("lecturer.profile.fullName")
    private String gvName;
    @JsonProperty("subject.name")
    private String subjectN;
    // phan mon
    private boolean isPhanMon;
    private String listGVStr;
    private List<Long> listGV;
    private List<LecturerDTO> lecturers;
    private Long khoa;
    private String className;
}
