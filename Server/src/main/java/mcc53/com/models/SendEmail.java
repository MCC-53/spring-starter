/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

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
public class SendEmail {

    private String to;
    private String subject;
    private String text;
}
