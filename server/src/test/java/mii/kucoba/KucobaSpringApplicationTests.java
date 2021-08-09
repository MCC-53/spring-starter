package mii.kucoba;

import mii.kucoba.models.Department;
import mii.kucoba.models.Employee;
import mii.kucoba.models.Project;
import mii.kucoba.service.access.DepartmentService;
import mii.kucoba.service.access.EmployeeService;
import mii.kucoba.service.access.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KucobaSpringApplicationTests {

//	@Test
//	void contextLoads() {
//	}
    
//    @Autowired
//    EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private EmployeeService service;
    
    @Autowired
    private ProjectService projectService;
    
    @Test
    void dumyDataDepartment(){
//        create data department
        Department department = Department.builder()
                .name("Kesehatan")
                .build();
        
        departmentService.createDepartment(department);
        
//         create data employee
        Employee employee = Employee.builder()
            .name("Abiyoga")
            .email("abiyoga@hendra")
            .address("jalan kenanga")
            .last_name("wijaya")
            .department(department)
            .build();
        
        service.insertDataEmployee(employee);
        
        Project p = Project.builder()
                .lokasi("jogja")
                .name("project1")
                .build();
       
        projectService.createProjectData(p);

    }
    
    
    
//    @Test
//    void dumyDataEmployee(){
//        
//        Department department = Department.builder()
//                .id(1)
//                .build();
//        
//    }
  
}
