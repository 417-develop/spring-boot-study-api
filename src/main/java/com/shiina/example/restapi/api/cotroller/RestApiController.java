package com.shiina.example.restapi.api.cotroller;

import javax.validation.Valid;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeAsyncRequest;
import com.amazonaws.services.lambda.model.InvokeAsyncResult;
import com.shiina.example.restapi.client.ExternalClient;
import com.shiina.example.restapi.model.ApiError;
import com.shiina.example.restapi.model.ExternalRequest;
import com.shiina.example.restapi.model.ExternalResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiController {
    private final ExternalClient client;

    @GetMapping("/")
    public String get() {
        return "helloWorld";
    }

    @PostMapping("/{id}")
    public ExternalResponse post(@PathVariable("id") String id) {
        ExternalRequest request = new ExternalRequest();
        request.setId("1");
        ExternalResponse response = client.post(ExternalClient.POST_USERS, request, ExternalResponse.class);
        return response;
    }

    @PostMapping("/ids")
    public ResponseEntity post(@RequestBody @Valid ExternalRequest id, Errors errors) {
        if (errors.hasErrors()) {
            String messsage = "";
            for (FieldError var : errors.getFieldErrors()) {
                messsage = messsage + var.getDefaultMessage();
            }
            return new ResponseEntity(new ApiError("400", messsage), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
