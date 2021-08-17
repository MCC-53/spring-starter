/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    
    private String firstname;
    private String lastname;
    private String email;
    private String addres;
    private Long department_id;
    private String username;
    private String pasword;
    
    
}
