/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.models;

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
public class Project {

    private Integer id;
    private String name;
    private String description;
}
