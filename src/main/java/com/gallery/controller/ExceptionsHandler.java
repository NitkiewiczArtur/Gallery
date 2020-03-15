package com.gallery.controller;

import com.gallery.exeption.GalleryNameAlreadyTakenException;
import com.gallery.exeption.GalleryNameIsNullException;
import com.gallery.exeption.NoPhotoUploadedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
@Slf4j
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoPhotoUploadedException.class)
    public ResponseEntity<Object> handleWebException(NoPhotoUploadedException e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_ACCEPTABLE, webRequest);
    }
    @ExceptionHandler(GalleryNameIsNullException.class)
    public ResponseEntity<Object> handleWebException(GalleryNameIsNullException e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_ACCEPTABLE, webRequest);
    }
    @ExceptionHandler(GalleryNameAlreadyTakenException.class)
    public ResponseEntity<Object>
    handleWebException(GalleryNameAlreadyTakenException e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, webRequest);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleWebException(Exception e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
