package com.nieceoftimes.serverside.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse  {
    private final String type = "failed";
    private final String message;
}
