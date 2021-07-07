/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AdministratorsDAO;
import java.util.ArrayList;
import models.Administrator;
import models.User;
import view.login.Login;

/**
 *
 * @author Dyogo
 */
public class AdministratorController {
    AdministratorsDAO dao;
    Administrator adminModel; 
    
    public AdministratorController(){
        this.dao = new AdministratorsDAO();        
    }
    
    public void login(String username, String password) throws Exception{
        System.out.println(username + " " + password);
        var data =  this.dao.get();        
        boolean allowed = false;   
        System.out.println(data);        
        
        for (int i = 0; i < data.size(); i ++){
            var d = (Administrator) data.get(i);
            if (d.getName().equals(username) && d.getPassword().equals(password)){
                allowed = true;
            }
        }
                
       if(!allowed){
           throw new Exception("Ta proibido de passa amizade");
       }
       
    }
    
}
