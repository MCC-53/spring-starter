package com.nieceoftimes.serverside.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private final String type = "success";
    private String message;
    private Object content;

    public ApiResponse(String message) {
        this.message = message;
    }
}
