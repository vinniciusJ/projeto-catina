/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ManagerDAO;
import java.util.ArrayList;
import models.Manager;

/**
 *
 * @author Dyogo
 */
public class ManagerController {
    ManagerDAO dao;
    Manager managerModel; 
    
    public ManagerController(){
        this.dao = new ManagerDAO();        
    }
    
    public boolean login(String username, String password) throws Exception{        
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
       return allowed;
    }                
    
}
