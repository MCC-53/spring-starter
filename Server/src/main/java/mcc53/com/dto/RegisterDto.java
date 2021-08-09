/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Xvitas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
//    attribut2 yg ada di tabel employee
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Integer department_id;
//    attribut2 yg ada di tabel user    
    private String username;
    private String password;
    
}
