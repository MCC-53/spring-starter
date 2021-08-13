package mcc53.com;

import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.services.DepartmentService;
import mcc53.com.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringProjectApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void counterTest() {
//        Counter counter1 = applicationContext.getBean(Counter.class);
//        Counter counter2 = applicationContext.getBean(Counter.class);
//        Counter counter3 = applicationContext.getBean(Counter.class);
//
//        counter1.counter();
//        counter2.counter();
//        counter3.counter();
//
//        System.out.println(counter1.getValue());
//        System.out.println(counter2.getValue());
//        System.out.println(counter3.getValue());
    }

//    @Test
//    void prototypeTest() {
//        Produk produk1 = applicationContext.getBean("produkSmartPhone", Produk.class);
//        produk1.setName("Samsung");
//        produk1.setCategory("smartphone");
//        System.out.println(produk1.getHarga());
//
//
//    }
//    @Autowired
//    EmployeeService employeeService;
//
//    @Autowired
//    DepartmentService departmentService;
//
//    @Test
//    void dummyDataEmployee() {
//        Department department = new Department()
//                .builder()
//                .name("Data Engineer")
//                .build();
//        departmentService.createDepartement(department);
//
//        Employee employee = new Employee()
//                .builder()
//                .firstName("Rory")
//                .lastName("Ramadhan")
//                .email("rory@gmail.com")
//                .address("jakarta")
//                .department(department)
//                .build();
//        employeeService.createEmployee(employee);
//
//        System.out.println(employee.toString());
//    }
}
