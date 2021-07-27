/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.utils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author WahyuKu
 */
public class Main {
    
    public static void main(String[] args) {
        Student<String[]> student = new Student();
        List<Data> kode = new ArrayList();
        
        kode.add(new Data("Samuel", 12));
        kode.add(new Data("Sam", 13));
        kode.add(new Data("Samu", 14));
        String[] data = {"Uzumaki", "Naruto"};
        student.setKodeUndian(data);
        
        for (String integer : student.getKodeUndian()) {
            System.out.println(integer.toString());
        }
    }
}

class Data {
    private String fullName;
    private Integer nim;

    public Data(String fullName, Integer nim) {
        this.fullName = fullName;
        this.nim = nim;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNim() {
        return nim;
    }

    public void setNim(Integer nim) {
        this.nim = nim;
    }
}

class Student <T> {
    private T kodeUndian;

    public Student() {
    }

    public T getKodeUndian() {
        return kodeUndian;
    }

    public void setKodeUndian(T kodeUndian) {
        this.kodeUndian = kodeUndian;
    }
}