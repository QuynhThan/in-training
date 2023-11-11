package com.ms.training.application.controller;

import com.ms.training.application.constant.ApiConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ApiConstant.V1 + ApiConstant.USER)
public class TimetableController {

    @PostMapping("/timetable/view")
    public ResponseEntity<Object> addSubject(@RequestBody Object o){
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
