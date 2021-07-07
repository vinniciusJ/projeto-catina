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
public class Administrator extends User {    
    public Administrator(String name, String password){
        this.name = name;
        this.password = password;
        this.fullAcess = false;
    }

    @Override
    public String toString() {
        return "Administrator{" 
                + "name: " + this.name + "\n" +
                "password: " + this.password + "\n" +
                "fullAcess: " + this.fullAcess + "\n"
                + '}';
    }
    
    
}
