/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author haikal
 * @param M = list of message.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseMessage <M> {
    private M data;
    private String message;
}
