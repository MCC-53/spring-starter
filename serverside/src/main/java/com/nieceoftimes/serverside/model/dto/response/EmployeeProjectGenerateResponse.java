package com.nieceoftimes.serverside.model.dto.response;

import com.nieceoftimes.serverside.model.entity.Project;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeProjectGenerateResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private List<Project> projects;
}
