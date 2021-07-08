/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import models.Manager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Dyogo
 */
public class ManagerDAO implements DAOInterface {    
    Connection connection = new Connection("manager");
    JSONParser jsonParser = new JSONParser();
    
    @Override
    public void delete(Object object) {
        String data = object.toString();        
        this.connection.delete(data);        
    }

    @Override
    public ArrayList<Object> get() {        
        JSONArray data = this.connection.read();        
        ArrayList<Object> managers = new ArrayList();        
        
        data.forEach((manager) -> {            
            managers.add(this.parseManager((JSONObject)manager));
        });        
        return managers;
    }

    @Override
    public void post(Object object) {        
        this.connection.write(object.toString());
    }

    @Override
    public void put(Object oldObject, Object newObject) {
        this.connection.replace(oldObject.toString(), newObject.toString());
    }
    
    private Manager parseManager(JSONObject manager){        
        String name = (String) manager.get("name");
        String password = (String) manager.get("password");     
        String acess = (String) manager.get("fullAcess");
        boolean fullAcess = Boolean.valueOf (acess);         
        
        Manager mng = new Manager(name, password, fullAcess);        
        
        return mng;
    }
    
    
}
