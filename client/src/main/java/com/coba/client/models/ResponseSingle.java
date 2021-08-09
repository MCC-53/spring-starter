package com.coba.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSingle<T> {
    private String status;
    private List<String> message;
    private T data;
}
