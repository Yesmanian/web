package com.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericDTO<T> {

    //지정된 값이 아니고 header, body 이렇게 정해져 들어올때 값 받기

    private Header header;

    private T body;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header{
        private String responseCode;
    }

}
