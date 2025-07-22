package com.example.financeproduct.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {
    private String field;
    private final String msg;

    private ErrorData(String field, String msg){
        this.field = field;
        this.msg = msg;
    }

    private ErrorData(String msg){
        this.msg = msg;
    }

    public static ErrorData error(String field, String msg){
        return new ErrorData(field, msg);
    }

    public static ErrorData error(String msg){
        return new ErrorData(msg);
    }
}
