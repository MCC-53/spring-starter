/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models.request;

import java.util.List;
import lombok.Data;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;

/**
 *
 * @author Xvitas
 */
@Data
public class EmployeeDepartment {
    
    private Department department;
    private List<Employee> employees;

    public EmployeeDepartment() {
    }
}
