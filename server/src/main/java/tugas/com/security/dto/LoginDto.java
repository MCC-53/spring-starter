/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author putug
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {
    private String username;
    private String password;
}
