package com.elk.common.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such entity")
@Getter
public class EntityNotFoundException extends RuntimeException {

    private final Class entityClass;
    private final Long entityId;

    public EntityNotFoundException(Class entityClass, Long entityId) {
        this.entityClass = entityClass;
        this.entityId = entityId;
    }
}
