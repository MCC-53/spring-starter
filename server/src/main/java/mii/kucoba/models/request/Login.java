/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.models.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mii.kucoba.models.Role;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author abiyo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Login {
    
    private String username;
    
    private String password;
    
    private List<GrantedAuthority> roles;
}
