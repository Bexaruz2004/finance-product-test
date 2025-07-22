package com.example.financeproduct.exceptions;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException{
    private final String msg;

    public RestException(String msg){
        super(msg);
        this.msg = msg;
    }
}
