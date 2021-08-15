/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.app.client.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Login {
    
    private String username;
    private String password;
    
}
