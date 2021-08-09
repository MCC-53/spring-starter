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

/**
 *
 * @author abiyo
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Authorization {
    
    private List<String> auth;
}
