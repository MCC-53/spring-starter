package com.mcc53jpa.models;

public class RespondMessage<E>{
    private E data;
    private String message;

    public RespondMessage(String message) {
        this.message = message;
    }

    public RespondMessage(E data) {
        this.data = data;
    }

    public RespondMessage(E data, String message) {
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
