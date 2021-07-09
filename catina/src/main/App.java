/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.AppController;

/**
 *
 * @author Vinicius Jimenez
 */
public class App {
    AppController controller;
    
    public App(){
        this.controller = new AppController();
    }

    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void initialize(){
        var app = new App();
        
        app.init();
    }
}
