/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Xvitas
 */
@Data
@AllArgsConstructor
public class ResponseEmployee {
    
    private String firstName;
    private String lastName;
    private Long id;

    public ResponseEmployee() {
    }
}
