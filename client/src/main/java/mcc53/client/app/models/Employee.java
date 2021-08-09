/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.models;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author haikal
 * @param D = hasil department
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Employee {
    private Integer id;
    private String name;
    private String last_name;
    private String email;
    private String address;
    private Department department;
//    private User user;
//    private List<Project> projects;
}
