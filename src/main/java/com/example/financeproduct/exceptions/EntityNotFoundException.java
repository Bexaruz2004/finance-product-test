package com.example.financeproduct.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{
    private final String msg;

    public EntityNotFoundException(String entity, String field, Object value) {
        super(String.format("%s not found by %s: %s", entity, field, value));
        this.msg = String.format("%s not found by %s: %s", entity, field, value);
    }
}
