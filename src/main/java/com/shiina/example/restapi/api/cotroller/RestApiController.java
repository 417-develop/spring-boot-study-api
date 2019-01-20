package com.shiina.example.restapi.api.cotroller;

import com.shiina.example.restapi.client.ExternalClient;
import com.shiina.example.restapi.model.ExternalRequest;
import com.shiina.example.restapi.model.ExternalResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiController {
    private final ExternalClient client;

    @GetMapping("/")
    public ExternalResponse get() {
        ExternalResponse response = client.get(ExternalClient.GET_USERS, ExternalResponse.class);
        return response;
    }

    @PostMapping("/{id}")
    public ExternalResponse post(@PathVariable("id") String id) {
        ExternalRequest request = new ExternalRequest();
        request.setId("1");
        ExternalResponse response = client.post(ExternalClient.POST_USERS, request, ExternalResponse.class);
        return response;
    }
}
