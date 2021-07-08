package main;


import models.Manager;

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
    private static Manager MANAGER;

    /**
     * @return the MANAGER
     */
    public static Manager getUSER() {
        return MANAGER;
    }

    /**
     * @param USER the MANAGER to set
     */
    public static void setUSER(Manager USER) {
        MANAGER = USER;
    }
}
