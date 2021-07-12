/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.AppController;
import controllers.CanteenController;
import controllers.ManagerController;
/**
 *
 * @author Vinicius Jimenez
 */
public class App {
    AppController controller;
    
    public App(){
        var hasFullAcess = Environment.getUSER().isFullAcess();
        
        this.controller = hasFullAcess ? new ManagerController() : new CanteenController();
    }
    
    public void init(){
        this.controller.init();
    }


    public static void initialize(){
        var app = new App();

        app.init();
    }
}
