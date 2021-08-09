/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.models;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author ACER
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RegisterDto {
    private String email, firstName, lastName, phoneNumber, username, password;
    private Long departmentId;
    private List<Projects> projects;
    private List<Roles> roles;
    //private List<Privileges> privileges;
}