package com.edd.date.configException;

import lombok.Getter;

@Getter
public class CustomException extends Exception{
    private final ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
