package com.ms.training.application.controller;

import com.ms.training.application.constant.ApiConstant;
import com.ms.training.application.dto.request.SaveTimetableReq;
import com.ms.training.application.dto.request.SubmitTimeTableReq;
import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.dto.search.SearchRequest;
import com.ms.training.application.dto.training.*;
import com.ms.training.application.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ApiConstant.V1 + ApiConstant.ADMIN)
public class MaintenanceController {
    @Autowired
    MaintenanceService maintenanceService;

    @PostMapping("/subject-maintenance/add")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(maintenanceService.addSubject(subjectDTO), HttpStatus.OK);
    }

    @PostMapping("/subject-maintenance/update")
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(maintenanceService.updateSubject(subjectDTO), HttpStatus.OK);
    }

    @PostMapping("/subject-maintenance/delete")
    public ResponseEntity<SubjectDTO> deleteSubject(@RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(maintenanceService.deleteSubject(subjectDTO), HttpStatus.OK);
    }

    @PostMapping("/subject-maintenance/delete/{subjectId}")
    public ResponseEntity<SubjectDTO> deleteSubject(@PathVariable Long subjectId){
        SubjectDTO subjectDTO = SubjectDTO.builder().subjectId(subjectId).build();
        return new ResponseEntity<>(maintenanceService.deleteSubject(subjectDTO), HttpStatus.OK);
    }

    @PostMapping("/subject-maintenance/retrieve")
    public ResponseEntity<List<SubjectDTO>> retrieveAll(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.subjectRetrieve(request), HttpStatus.OK);
    }

    @PostMapping("/lecturers-maintenance/retrieve")
    public ResponseEntity<List<LecturerDTO>> retrieveAllLecturer(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.lecturerRetrieve(request), HttpStatus.OK);
    }

    @PostMapping("/classroom-maintenance/retrieve")
    public ResponseEntity<List<ClassroomDTO>> retrieveAllClassroom(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.classroomRetrieve(request), HttpStatus.OK);
    }

    @PostMapping("/class-credit-maintenance/retrieve")
    public ResponseEntity<List<ClassCreditDTO>> retrieveAllClassCredit(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.classCreditRetrieve(request), HttpStatus.OK);
    }
    @PostMapping("/student-class/retrieve")
    public ResponseEntity<List<StudentClassDTO>> retrieveAllStudentClass(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.studentClassRetrieve(request), HttpStatus.OK);
    }


    @PostMapping("/faculty-maintenance/retrieve")
    public ResponseEntity<List<FacultyDTO>> retrieveAllFaculty(@RequestBody(required = false) SearchRequest request) {
        return new ResponseEntity<>(maintenanceService.facultyRetrieve(request), HttpStatus.OK);
    }

    @PostMapping("/lecturers-maintenance/delete")
    public ResponseEntity<LecturerDTO> deleteLecturers(@RequestBody LecturerDTO lecturerDTO){
        return new ResponseEntity<>(maintenanceService.deleteLecturers(lecturerDTO), HttpStatus.OK);
    }

    @PostMapping("/lecturers-maintenance/add")
    public ResponseEntity<LecturerDTO> addLecturers(@RequestBody LecturerDTO lecturerDTO){
        return new ResponseEntity<>(maintenanceService.addLecturers(lecturerDTO), HttpStatus.OK);
    }

    @PostMapping("/lecturers-maintenance/update")
    public ResponseEntity<LecturerDTO> updateLecturers(@RequestBody LecturerDTO lecturerDTO){
        return new ResponseEntity<>(maintenanceService.updateLecturers(lecturerDTO), HttpStatus.OK);
    }

    @PostMapping("/classroom-maintenance/add")
    public ResponseEntity<ClassroomDTO> addClassroom(@RequestBody ClassroomDTO classroomDTO){
        return new ResponseEntity<>(maintenanceService.addClassroom(classroomDTO), HttpStatus.OK);
    }

    @PostMapping("/classroom-maintenance/update")
    public ResponseEntity<ClassroomDTO> updateClassroom(@RequestBody ClassroomDTO classroomDTO){
        return new ResponseEntity<>(maintenanceService.updateClassroom(classroomDTO), HttpStatus.OK);
    }

    @PostMapping("/classroom-maintenance/delete")
    public ResponseEntity<ClassroomDTO> deleteClassroom(@RequestBody ClassroomDTO classroomDTO){
        return new ResponseEntity<>(maintenanceService.deleteClassroom(classroomDTO), HttpStatus.OK);
    }

    @PostMapping("/class-credit-maintenance/add")
    public ResponseEntity<ClassCreditDTO> addClassCredit(@RequestBody ClassCreditDTO classCreditDTO){
        return new ResponseEntity<>(maintenanceService.addClassCredit(classCreditDTO), HttpStatus.OK);
    }

    @PostMapping("/class-credit-maintenance/update")
    public ResponseEntity<ClassCreditDTO> updateClassCredit(@RequestBody ClassCreditDTO classCreditDTO){
        return new ResponseEntity<>(maintenanceService.updateClassCredit(classCreditDTO), HttpStatus.OK);
    }

    @PostMapping("/class-credit-maintenance/delete")
    public ResponseEntity<ClassCreditDTO> deleteClassCredit(@RequestBody ClassCreditDTO classCreditDTO){
        return new ResponseEntity<>(maintenanceService.deleteClassCredit(classCreditDTO), HttpStatus.OK);
    }

    @PostMapping("/timetable/update")
    public ResponseEntity<Object> updateTimetable(@RequestBody Object o){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/timetable/submit")
    public ResponseEntity<Object> submitTimeTable(@RequestBody(required = false) SubmitTimeTableReq req){
        return new ResponseEntity<>(maintenanceService.submitTimeTable(req), HttpStatus.OK);
    }

    @PostMapping("/timetable/save")
    public ResponseEntity<Object> saveTimetable(@RequestBody SaveTimetableReq req) {
        return new ResponseEntity<>(maintenanceService.saveTimetable(req), HttpStatus.OK);
    }

    @PostMapping("/timetable/retrieve")
    public ResponseEntity<Object> retrieveTimetable(@RequestBody(required = false) SearchRequest req) {
        return new ResponseEntity<>(maintenanceService.retrieveTimetable(req), HttpStatus.OK);
    }
}
