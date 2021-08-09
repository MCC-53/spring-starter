package com.nieceoftimes.clientside.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseListData<T> {
    private String type;
    private String message;
    private List<T> content;
}
