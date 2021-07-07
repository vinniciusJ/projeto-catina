/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import models.Administrator;

/**
 *
 * @author Dyogo
 */
public class AdministratorsDAO implements DAOInterface {    
    Connection connection = new Connection("administrators");
    JSONParser jsonParser = new JSONParser();
    
    @Override
    public void delete(Object object) {
        String data = object.toString();        
        this.connection.delete(data);        
    }

    @Override
    public ArrayList<Object> get() {        
        JSONArray data = this.connection.read();        
        ArrayList<Object> admins = new ArrayList();
        
        data.forEach((admin) -> {
            admins.add(this.parseAdministrators((JSONObject)admin));
        });
                
        return admins;
    }

    @Override
    public void post(Object object) {        
        this.connection.write(object.toString());
    }

    @Override
    public void put(Object oldObject, Object newObject) {
        this.connection.replace(oldObject.toString(), newObject.toString());
    }
    
    private Administrator parseAdministrators(JSONObject admin){
        String name = (String) admin.get("name");
        String password = (String) admin.get("password");        
        
        Administrator adm = new Administrator(name, password);
        
        return adm;
    }
    
}
