/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DAO;
import main.App;
import main.Environment;
import models.Manager;
import view.login.LoginView;
import view.login.RegisterView;

/**
 *
 * @author Dyogo
 */
public class LoginController {
    DAO managerDAO;
    Manager managerModel; 
    LoginView view;
    
    public LoginController(){                
        this.managerDAO = new DAO(Manager.class);   
        this.view = new LoginView();
    }
    
    public void init(){
        this.view.addSubmitEvent((username, password) -> {
            try {
                this.login(username, password);
                this.accessSystem();
                
            } catch (Exception ex) {
                this.view.notifyUnmatchedCredentials();
            }
        });
        
        this.view.addRegisterEvent(event -> {
            this.view.closeLoginWindow();
            
            var registerView = new RegisterView();

            registerView.onSave(data -> {
                var username = (String) data.get("username");
                var password = (String) data.get("password");
                var canteenName = (String) data.get("canteenName");
                
                registerView.close();
                
                this.view.setCredentials(username, password);
                this.view.paint();
            });
            
            registerView.onCancel(data -> {
                registerView.close();
                
                this.view.paint();
            });
        });
    }
    
    public void accessSystem(){
        this.view.closeLoginWindow();
        
        App.initialize();
    }
    
    public void registerUser(String canteenName, String userName, String passoword){
        
    }
    
    public void login(String username, String password) throws Exception{        
        var data =  this.managerDAO.get();        
        var exists = false;
        Manager currentManager = null;
        
        for (int i = 0; i < data.size(); i ++){
            var datum = (Manager) data.get(i);            

            if (datum.getName().equals(username) && datum.getPassword().equals(password)){                
                exists = true;                
                currentManager = datum;
            }
        }        
                
        if(!exists){
           throw new Exception("Ta proibido de passa amizade");           
        }              
      
        Environment.setUser(currentManager);
    }                
        
    public static void initialize(){
        var login = new LoginController();
        
        login.init();
    }
}
