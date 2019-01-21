package com.shiina.example.restapi.model;

import java.util.Arrays;
import java.util.List;

import com.shiina.example.restapi.api.cotroller.BadRequestExceptionHandler;
import com.shiina.example.restapi.exception.BadRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
 
    private String status;
    private String message;
 
    public ApiError(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public static ResponseEntity<ApiError> createResponse(BadRequestException e){
        return new ResponseEntity<ApiError>(
                new ApiError(e.getStatus(),e.getMessage())
                    ,HttpStatus.BAD_REQUEST);
    }
}

