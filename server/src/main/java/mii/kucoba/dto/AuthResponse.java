/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author haikal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
   private List<String> authorities;
}
    