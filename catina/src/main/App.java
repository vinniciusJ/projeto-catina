/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.CanteenController;
/**
 *
 * @author Vinicius Jimenez
 */
public class App {
    CanteenController controller;
    
    public App(){

        this.controller = new CanteenController();
    }
    
    public void init(){
        this.controller.init();
    }


    public static void initialize(){
        var app = new App();

        app.init();
    }
}
