package com.mcc53jpa.repositories;

import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employee WHERE first_name = ?", nativeQuery = true)
    public List<Employee> findEmployeeWithName(String name);

    List<Employee> findByDepartment_id(Long id);
//    Employee findByUser_Username(String username);
}
