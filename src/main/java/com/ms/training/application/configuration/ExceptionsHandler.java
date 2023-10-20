package com.ms.training.application.configuration;

import com.ms.training.application.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> businessError(BusinessException e) {
        Error error = new Error();
//        error.setErrorCode("");
//        error.setErrorMessage(e.getMessage());
        return ResponseEntity.ok(error);
    }
}
