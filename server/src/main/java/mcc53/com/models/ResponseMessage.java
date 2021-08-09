/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WahyuKu
 * @param <E> entity response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage <E> {
    private boolean status; //Menampung status API nanti, true jika jalan, false jika gagal
    private List<String> message = new ArrayList<>();//List yang menampung message error nanti
    private E Data;//tempat menampung data yang masuk

}
