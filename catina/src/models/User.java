/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Dyogo
 */
public class User {
    String name;
    String password;
    boolean fullAcess;
    
    public User(String name, String password, boolean fullAcess){
        this.name = name;
        this.password = password;
        this.fullAcess = fullAcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
