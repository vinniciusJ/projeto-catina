/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.DAO;
import org.json.simple.JSONObject;

/**
 *
 * @author Dyogo
 */
public class Manager implements ModelDatabase{
    String id;
    String name;
    String password;
    boolean fullAcess;  
    Canteen canteen;
    
    public Manager(JSONObject fields){
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.password = (String) fields.get("password");
        this.fullAcess = Boolean.valueOf(fields.get("fullAcess").toString());
        
        String canteenId = (String) fields.get("canteenId");
        var canteenDAO = new DAO(Canteen.class);
        this.canteen = (Canteen) canteenDAO.get(canteenId);

    }

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }
    
    public Manager(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFullAcess() {
        return fullAcess;
    }

    public void setFullAcess(boolean fullAcess) {
        this.fullAcess = fullAcess;
    }

    @Override
    public String databaseName() {
        return "Manager";
    }

    @Override
    public String getId() {
        return this.id;
    }
    
    
    
}
