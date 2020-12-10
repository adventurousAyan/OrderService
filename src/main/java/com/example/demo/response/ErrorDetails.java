package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private List<String> errors;

    public ErrorDetails(Date timestamp, String message, String errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errors = Arrays.asList(errors);
    }

    public ErrorDetails(Date timestamp, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }


}