/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author abiyo
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeProject {
    
    
    private Integer idEmployee;
    private String name;
    private String email;
    private String lastname;
    private String address;
    private Integer idProject;
    private String projectName;
    private String projectLocation;
}
