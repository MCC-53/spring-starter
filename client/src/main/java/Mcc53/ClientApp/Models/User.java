/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Models;

import java.util.List;

/**
 *
 * @author putug
 */
public class User {
    private String username;
    private List<String> permissions;

    public User(String username, List<String> permissions) {
        this.username = username;
        this.permissions = permissions;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
