/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ManagerDAO;
import main.App;
import main.Environment;
import models.Manager;
import view.login.Login;

/**
 *
 * @author Dyogo
 */
public class ManagerController {
    ManagerDAO dao;
    Manager managerModel; 
    Login view;
    
    public ManagerController(){
        this.dao = new ManagerDAO();   
        this.view = new Login();
        
        this.view.addSubmitEvent((username, password) -> {
            try {
                this.login(username, password);
                this.accessSystem();
                
            } catch (Exception ex) {
                this.view.notifyUnmatchedCredentials();
            }
        });
    }
    
    public void accessSystem(){
        this.view.closeLoginWindow();
        
        var app = new App();
        
        app.init();
    }
    
    public void login(String username, String password) throws Exception{        
        var data =  this.dao.get();        
        boolean allowed = false, exists = false;
    
        
        for (int i = 0; i < data.size(); i ++){
            var d = (Manager) data.get(i);            
            if (d.getName().equals(username) && d.getPassword().equals(password)){                
                exists = true;
                allowed = d.isFullAcess();
            }
        }        
                
        if(!exists){
           throw new Exception("Ta proibido de passa amizade");           
        }              
      
        var manager = new Manager(username, password, allowed);
       
        Environment.setUSER(manager);
    }                
    
    
}
