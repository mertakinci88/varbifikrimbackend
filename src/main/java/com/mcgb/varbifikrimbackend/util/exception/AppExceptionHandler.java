package com.mcgb.varbifikrimbackend.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleGenericExceptions(Exception ex) {
        GenericException genericExc = new GenericException();
        genericExc.setStatus(HttpStatus.BAD_REQUEST);
        genericExc.setMessage(ex.getMessage());
        return new ResponseEntity(genericExc, HttpStatus.BAD_REQUEST);
    }

    // Handle business exceptions
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity handleBusinessExceptions(BusinessException ex) {
        GenericException genericExc = new GenericException();
        genericExc.setStatus(HttpStatus.BAD_REQUEST);
        genericExc.setMessage(ex.getMessage());
        return new ResponseEntity(genericExc, HttpStatus.BAD_REQUEST);
    }

    // Handle validation exceptions
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        HashMap validationErrors = new HashMap();
        fieldErrorList.forEach(fieldError -> {
            if (!validationErrors.containsKey(fieldError.getField())) {
                List messageList = new ArrayList();
                messageList.add(fieldError.getDefaultMessage());
                validationErrors.put(fieldError.getField(), messageList);
            }
            else {
                ((List)validationErrors.get(fieldError.getField())).add(fieldError.getDefaultMessage());
            }
        });
        GenericException genericExc = new GenericException();
        genericExc.setStatus(HttpStatus.BAD_REQUEST);
        genericExc.setMessage("Doğrulama hatası.");
        genericExc.setValidationErrors(validationErrors);
        return new ResponseEntity(genericExc, HttpStatus.BAD_REQUEST);
    }
}
