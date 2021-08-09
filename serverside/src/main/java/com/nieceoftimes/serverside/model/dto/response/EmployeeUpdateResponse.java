package com.nieceoftimes.serverside.model.dto.response;

import com.nieceoftimes.serverside.model.entity.Department;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeUpdateResponse {
    String firstName;
    String lastName;
    String email;
    String address;
    Department department;
}
