/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.front.models.request;

import java.util.List;
import lombok.*;

/**
 *
 * @author abiyo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRole {
    
    List<String> auth;
}
