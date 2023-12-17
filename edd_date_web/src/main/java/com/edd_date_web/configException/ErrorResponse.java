package com.edd.date.configException;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String errorCode;
    private String message;


    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

}
