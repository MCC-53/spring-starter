/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author haikal
 * @param <E> result data entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData <E> {
        
    private Integer count;
    private String next;
    private String previous;
    private List<E> results;
    
}
