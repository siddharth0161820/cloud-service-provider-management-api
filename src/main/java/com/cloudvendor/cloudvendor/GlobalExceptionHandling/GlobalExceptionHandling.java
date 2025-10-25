package com.cloudvendor.cloudvendor.GlobalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    //Method()-1
    @ExceptionHandler
    public ResponseEntity<Object>response(NOTFOUNDEXCEPTION notfoundexception){
        EntityExceptionModel entityExceptionModel=new EntityExceptionModel(
                notfoundexception.getMessage(),
                HttpStatus.NOT_FOUND
        );
       return new ResponseEntity<>(entityExceptionModel,HttpStatus.NOT_FOUND);
    }

    //Method()-2
    @ExceptionHandler
    public ResponseEntity<Object>response(MethodArgumentNotValidException e){
        //1>Create a Map to store the Errors
        Map<String,Object>map=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((errors)->{
            map.put(errors.getField(),errors.getDefaultMessage());
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}
