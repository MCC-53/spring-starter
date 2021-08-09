/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author haikal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {
    private String name;
    private String last_name;
    private String address;
    private String email;
    private Integer department_id;
    private String username;
    private String password;
}
