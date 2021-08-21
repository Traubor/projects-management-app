package com.elk.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such entity")
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFound(EntityNotFoundException ex) {
        log.warn("Entity of type {} and id: {} not found", ex.getEntityClass().getName(), ex.getEntityId());
    }
}
