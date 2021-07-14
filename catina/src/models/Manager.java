/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.DAO;
import java.util.Locale;
import org.json.simple.JSONObject;

/**
 *
 * @author Dyogo
 */
public class Manager extends ModelDatabase{    
    String name;
    String password;    
    Canteen canteen;
    
    public Manager(JSONObject fields){
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.password = (String) fields.get("password");                
        String canteenId = (String) fields.get("canteenId");
        var canteenDAO = new DAO(Canteen.class);
        this.canteen = (Canteen) canteenDAO.get(canteenId);               
    }
    
    public Manager(String name, String password, Canteen canteen){
        this.name = name;
        this.password = password;
        this.canteen = canteen;
        this.setId();
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

    @Override
    public String databaseName() {
        return "Manager";
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toJSONString() {
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"canteenId\": \"%s\", \"name\": \"%s\", \"password\": \"%s\"}", this.id, this.canteen.getId(), this.name, this.password);        
        return s;
    }
    
    
    
}
