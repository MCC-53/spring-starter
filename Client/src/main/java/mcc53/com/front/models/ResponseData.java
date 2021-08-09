/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Xvitas
 * @param <E>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<E> {

    private List<E> data;
}
