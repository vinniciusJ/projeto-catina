/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.AppController;
import controllers.CanteenController;
/**
 *
 * @author Vinicius Jimenez
 */
public class App {
    AppController controller;
    
    public App(){
        this.controller = new CanteenController();
    }


    public static void initialize(){
        var app = new App();

    }
}
