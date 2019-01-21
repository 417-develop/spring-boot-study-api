package com.shiina.example.restapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends Exception {

    private static final long serialVersionUID = 1L;
    private String status;
    private String message;

    public BadRequestException(String status, String message){
        this.status = status;
        this.message = message;
    }

}