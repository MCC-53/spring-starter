/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

/**
 *
 * @author Xvitas
 * @param <E> entity response
 */
public class ResponseMessage <E> {
    private E  data;
    private String message;
    
    public ResponseMessage(E data, String message) {
        this.data = data;
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
