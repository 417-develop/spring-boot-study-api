package com.shiina.example.restapi.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExternalClient {

    //TODO: Change to Abstract class. add getbaseUrl(): abstract method.

    private final RestTemplate restTemplate;

    @Value("${extatnal.base.url}")
    private String baseUrl;

    public static final String GET_USERS = "/users";
    public static final String POST_USERS = "/users";

    public <T> T get(String uri, HashMap<String, String> params, Class<T> responseClass) {
    HttpEntity<?> entity = new HttpEntity<>(getHeaders());
    ResponseEntity<T> responseEntity = restTemplate.exchange(baseUrl + uri, HttpMethod.GET, entity, responseClass, params);
    return responseEntity.getBody();
    }

    public <T> T get(String uri, Class<T> responseClass) {
        HttpEntity<?> entity = new HttpEntity<>(getHeaders());
        ResponseEntity<T> responseEntity = restTemplate.exchange(baseUrl + uri, HttpMethod.GET, entity, responseClass);
        return responseEntity.getBody();
        }

    public <T> T post(String uri, Object body, Class<T> responseClass) {
        HttpEntity<?> entity = new HttpEntity<>(body, getHeaders());
        ResponseEntity<T> responseEntity = restTemplate.exchange(baseUrl + uri, HttpMethod.POST, entity, responseClass);
        return responseEntity.getBody();
    }

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return headers;
    }



}
