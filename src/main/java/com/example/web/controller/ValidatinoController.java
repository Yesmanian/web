package com.example.web.controller;

import com.example.web.dto.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/val")
public class ValidatinoController {

    @PostMapping("/req")
    public ResponseEntity user(@Validated @RequestBody Request req, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("feild : "+field.getField());
                System.out.println(message);

                sb.append("feild :"+field.getField());
                sb.append("message"+message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(req);
    }

}
