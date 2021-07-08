package main;


import models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius Jimenez
 */
public class Environment {
    private static User USER;

    /**
     * @return the USER
     */
    public static User getUSER() {
        return USER;
    }

    /**
     * @param aUSER the USER to set
     */
    public static void setUSER(User aUSER) {
        USER = aUSER;
    }
}
