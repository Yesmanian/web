package com.example.web.service;

import com.example.web.dto.GenericDTO;
import com.example.web.dto.Request;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {


    public Request hello(){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","ian")
                .encode()
                .build().toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        //받고싶은 타입으로 지정
//        String result = restTemplate.getForObject(uri,String.class);
        //받고싶은 타입으로 지정(더 많은 정보가 담겨있다)
        ResponseEntity<Request> result = restTemplate.getForEntity(uri,Request.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();

    }

    public Request hello2(){

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/hello/{userId}/{telNp}")
                .encode()
                .build()
                .expand("ian","010")
                .toUri();


        Request request =  new Request();
        request.setAge(10);
        request.setName("ian");

        RestTemplate restTemplate = new RestTemplate();
        //uri. 보낼 녀석, 받을 타입
        ResponseEntity<Request> response =  restTemplate.postForEntity(uri,request, Request.class);

        return response.getBody();
    }


    public Request exchange(){

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/hello/{userId}/{telNp}")
                .encode()
                .build()
                .expand("ian","010")
                .toUri();


        Request request =  new Request();
        request.setAge(10);
        request.setName("ian");

        RestTemplate restTemplate = new RestTemplate();

        //header 등등 보내고싶은거 다 묶어서보낼때 requestEntitiy exchange씀
        RequestEntity<Request> requestRequestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abbb")
                .body(request);

        ResponseEntity<Request> response = restTemplate.exchange(requestRequestEntity,Request.class);


        return response.getBody();

    }


    public Request genericExchange(){

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/hello/{userId}/{telNp}")
                .encode()
                .build()
                .expand("ian","010")
                .toUri();


        Request request =  new Request();
        request.setAge(10);
        request.setName("ian");
//////
        GenericDTO<Request> requestGenericDTO = new GenericDTO<Request>();
        requestGenericDTO.setHeader(new GenericDTO.Header());
        requestGenericDTO.setBody(request);


        RestTemplate restTemplate = new RestTemplate();

        //header 등등 보내고싶은거 다 묶어서보낼때 requestEntitiy exchange씀
        RequestEntity<GenericDTO<Request>> requestRequestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abbb")
                .body(requestGenericDTO);
        //generic에는 class를 붙일수가 없다                                                              error
//        ResponseEntity<GenericDTO<Request>> response = restTemplate.exchange(requestRequestEntity,GenericDTO<Request>.class);
        ResponseEntity<GenericDTO<Request>> response = restTemplate.exchange(requestRequestEntity,
                new ParameterizedTypeReference<GenericDTO<Request>>() {
                });

        return response. getBody().getBody();

    }
}
