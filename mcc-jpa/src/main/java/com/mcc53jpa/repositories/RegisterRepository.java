package com.mcc53jpa.repositories;

import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.User;
import com.mcc53jpa.models.request.RegisterRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository{
    Employee saveEmployee(RegisterRequest registerRequest);
    User saveUser(RegisterRequest registerRequests);
}
