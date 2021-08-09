package com.nieceoftimes.clientside.model;

import lombok.Data;

@Data
public class ResponseData <T>{
    private String type;
    private String message;
    private T content;
}
