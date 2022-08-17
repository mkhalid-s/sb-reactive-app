package com.khalid.sb3reactiveapp.exceptionhandler;

import com.khalid.sb3reactiveapp.exception.InputNumberValidationException;
import com.khalid.sb3reactiveapp.modal.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(InputNumberValidationException.class)
    public ResponseEntity<ExceptionResponse> handleInputValidationEx(InputNumberValidationException validationException){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrCode(InputNumberValidationException.getErrCode());
        exceptionResponse.setErrMsg(validationException.getMessage());
        exceptionResponse.setResponse(validationException.getResponseMsg());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

}
