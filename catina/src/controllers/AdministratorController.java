/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AdministratorsDAO;
import main.Environment;
import models.Administrator;
import models.User;

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
        var data =  this.dao.get();        
        boolean allowed = false;        
        
        for (int i = 0; i < data.size(); i ++){
            var d = (Administrator) data.get(i);
            if (d.getName().equals(username) && d.getPassword().equals(password)){
                allowed = true;
            }
        }
                
        if(!allowed){
            throw new Exception("Ta proibido de passa amizade");
        }
        //var loggedUser = new User()
        //Environment.setUSER(new User());
    }
    
}
