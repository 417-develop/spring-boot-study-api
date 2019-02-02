package com.shiina.example.restapi.api.cotroller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.shiina.example.restapi.exception.BadRequestException;
import com.shiina.example.restapi.model.ApiError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> getException(HttpServletRequest req, BadRequestException e){

        return ApiError.createResponse(e);

    }

    /**
     * 400 - Bad Reque
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> notReadable(HttpServletRequest req){
        return new ResponseEntity<ApiError>(
                new ApiError("400","NotReadable")
                ,HttpStatus.BAD_REQUEST);
    }

    // /**
    //  * 400 - Bad Reque
    //  */
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ApiError> notFound(HttpServletRequest req,  MethodArgumentNotValidException e){
    //     return new ResponseEntity<ApiError>(
    //             new ApiError("400",e.getMessage())
    //             ,HttpStatus.BAD_REQUEST);
    // }

    /**
     * 404 - Not Found
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> notFound(HttpServletRequest req){
        return new ResponseEntity<ApiError>(
                new ApiError("404","Not Found")
                ,HttpStatus.NOT_FOUND);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> notSupported(HttpServletRequest req){
        return new ResponseEntity<ApiError>(
                new ApiError("405","Method Not Allowed")
                ,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> anotherException(Exception e, HttpServletRequest req){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

        // ログは、大事
        System.err.println("ERROR(500) " + (sdf.format((Calendar.getInstance()).getTime()))
                + ": remoteAddr:" + req.getRemoteAddr()
                + ", remoteHost:" + req.getRemoteHost()
                + ", requestURL:" + req.getRequestURL()
                );
        e.printStackTrace();

        return new ResponseEntity<ApiError>(
                new ApiError("500",e.getMessage())
                ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
