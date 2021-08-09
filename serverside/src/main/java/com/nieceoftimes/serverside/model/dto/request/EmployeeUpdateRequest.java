package com.nieceoftimes.serverside.model.dto.request;

import lombok.Getter;

@Getter
public class EmployeeUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String departmentId;
}
