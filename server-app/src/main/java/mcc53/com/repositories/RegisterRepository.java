package mcc53.com.repositories;

import mcc53.com.models.Employee;
import mcc53.com.models.RegisterRequest;
import mcc53.com.models.User;

public interface RegisterRepository {
    Employee saveEmployee(RegisterRequest registerRequest);
    User saveUser(RegisterRequest registerRequest);
}
