package com.example.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponse<T> {

    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;

    public CustomResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public CustomResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}


