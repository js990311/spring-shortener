package com.toyproject.shortener.dto.response;


import jdk.jshell.Snippet;
import lombok.Getter;

@Getter
public class ResponseWrapper<T> {
    private ResponseStatus status;
    private T data;

    public ResponseWrapper(ResponseStatus status, T data){
        this.data = data;
        this.status = status;
    }
}
