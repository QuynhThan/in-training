package com.ms.training.application.controller;

import com.ms.training.application.constant.ApiConstant;
import com.ms.training.application.dto.request.SubmitSubjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ApiConstant.V1 + ApiConstant.USER)
public class InTrainingController {
    @PostMapping("/subject-register/submit")
    public ResponseEntity<Object> addSubject(@RequestBody SubmitSubjectRequest request){
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
