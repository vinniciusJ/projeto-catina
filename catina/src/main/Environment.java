package main;


import javax.swing.ImageIcon;
import models.Canteen;
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
    private static Canteen CURRENT_CANTEEN;
    public final static ImageIcon LOGO_ICON = new ImageIcon("src/images/logo.png");

    /**
     * @return the MANAGER
     */
    public static Manager getUser() {
        return MANAGER;
    }

    /**
     * @param USER the MANAGER to set
     */
    public static void setUser(Manager USER) {
        MANAGER = USER;
    }

    /**
     * @return the CURRENT_CANTEEN
     */
    public static Canteen getCurrentCanteen() {
        return CURRENT_CANTEEN;
    }

    /**
     * @param aCURRENT_CANTEEN the CURRENT_CANTEEN to set
     */
    public static void setCurrentCanteen(Canteen aCURRENT_CANTEEN) {
        CURRENT_CANTEEN = aCURRENT_CANTEEN;
    }
}
