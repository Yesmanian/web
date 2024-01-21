package com.example.web.controller;


import com.example.web.dto.Request;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {


    /*
    Get
    */
    //test
    @GetMapping("/hello")
    public String hello(){
        return "hi";
    }

    ///pathvariable
//    @RequestMapping(path = "/hi/{name}", method = RequestMethod.GET)
//    public String hi2(@PathVariable(name = "name") String id){
//
//        System.out.println("id = " + id);
//        return id;
//    }
    @RequestMapping(path = "/hi/{name}", method = RequestMethod.GET)
    public String hi(@PathVariable String name){

        return name;
    }


    //query parameter 다받기
    @GetMapping(path = "/query")
    public String queryParam(@RequestParam Map<String,String> queryParam){

        queryParam.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

//        queryParam.forEach((key,value) -> {
//            System.out.println(key);
//            System.out.println(value);
//        });
        return "";
    }

    //하나하나
    @GetMapping(path = "/query2")
    public String queryParam2(@RequestParam(name = "name") String name,@RequestParam int age){
        return "";
    }

    //easy way! @RequsetParam 안씀
    //객체정의 후 받는거
    @GetMapping(path = "/query3")
    public String queryParam3(Request request){
        return request.toString();
    }

    /*
    Post
    */
    @PostMapping(path = "/post")
    public String post(@RequestBody Map<String, Object> requestData){

        requestData.forEach((key, value) -> {
            System.out.println("key = " + key);
            System.out.println("value = "+ value);
        });

        return "";
    }

    @PostMapping(path = "/post2")
    public String post2(@RequestBody Request requestData, HttpEntity<String> entity){

        //뭐가 오는지 모를때 그냥 찎어봐서 알수있다다
       System.out.println(entity.getBody());
        return requestData.toString();
    }

    //RestController는 return할때 객체이면 objectmapper에 의해 알아서 json으로 바꿔서 응답값을 준다
    @PutMapping(path = "/put")
    public Request request(@RequestBody Request request){
        return request;
    }
//    ttd
//            r2
//    logback
//            jpa


}
