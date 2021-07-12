/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.modelDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Dyogo
 */
public class DAO {    
    Connection connection;
    JSONParser jsonParser = new JSONParser();    
    private Class classIdentifier;            
    
    public DAO(Class <?> model){                
        this.classIdentifier = model;
        try {
            Method getDatabaseName = model.getMethod("databaseName", new Class[] {});            
            String databaseName = (String) getDatabaseName.invoke(this.classIdentifier.getDeclaredConstructor().newInstance());
            this.connection = new Connection(databaseName);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
                IllegalArgumentException | InstantiationException | InvocationTargetException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }        
        
    public void delete(modelDatabase object) {
        String data = object.toString();        
        this.connection.delete(data);        
    }
    
    public ArrayList<modelDatabase> get() {        
        JSONArray JSONData = this.connection.read();        
        ArrayList<modelDatabase> dataObjects = new ArrayList();        
        
        JSONData.forEach((data) -> {            
            dataObjects.add(this.parseData((JSONObject)data));
        });        
        return dataObjects;
    }
    
    public void post(modelDatabase object) {        
        this.connection.write(object.toString());
    }
    
    public void put(modelDatabase oldObject, modelDatabase newObject) {
        this.connection.replace(oldObject.toString(), newObject.toString());
    }
    
    private modelDatabase parseData(JSONObject data){                        
        Class [] cArg = new Class[1];
        cArg[0] = JSONObject.class;
        
        modelDatabase convertedData = null;
        try {
            convertedData = (modelDatabase) this.classIdentifier.cast(
                    this.classIdentifier.getDeclaredConstructor(cArg).newInstance(data));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException |SecurityException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return convertedData;
    }
    
}
