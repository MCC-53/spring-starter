package com.nieceoftimes.clientside.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorCustomizeController implements ErrorController {

    @RequestMapping("/error")
    public String errorHandle(HttpServletRequest httpServletRequest) {
        Object errorStatus = httpServletRequest
                .getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (errorStatus != null) {
            Integer httpStatusCode = Integer.valueOf(errorStatus.toString());

            if (httpStatusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404.html";
            }
        }
        return "error/error.html";
    }
}
