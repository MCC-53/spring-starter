/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aceng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    private Long id;
    private String name;
    private String description;
}
