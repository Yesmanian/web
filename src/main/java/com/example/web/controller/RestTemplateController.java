package com.example.web.controller;

import com.example.web.dto.Request;
import com.example.web.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {

    private final RestTemplateService restTemplateService;


    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public Request getHello(){
        return restTemplateService.hello();
    }
}
