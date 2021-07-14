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
import models.ModelDatabase;
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
        
    public void delete(ModelDatabase object) {
        String data = object.toString();
        
        this.connection.delete(data);        
    }
    
    public ArrayList<ModelDatabase> get() {             
        
        JSONArray JSONData = this.connection.read();             
        ArrayList<ModelDatabase> dataObjects = new ArrayList();        
        
        JSONData.forEach((data) -> {                     
            dataObjects.add(this.parseData((JSONObject)data));
            
        });        
        return dataObjects;
    }
    
    public ModelDatabase get(String id){
        var data = this.get();        
        ModelDatabase object = null;
       
        var iterator = data.iterator();
        
        while(iterator.hasNext()){
            var datum = iterator.next();                        
            if(datum.getId().equals(id)){
                object = datum;
            }
        }        
        return object;
    }
    
    public void post(ModelDatabase object) {        
        this.connection.write(object.toJSONString());
    }
    
    public void put(ModelDatabase oldObject, ModelDatabase newObject) {
        this.connection.replace(oldObject.toString(), newObject.toString());
    }
    
    private ModelDatabase parseData(JSONObject data){          
        
        Class [] cArg = new Class[1];
        cArg[0] = JSONObject.class;
                        
        ModelDatabase convertedData = null;
        try {     
            convertedData = (ModelDatabase) this.classIdentifier.cast(
            this.classIdentifier.getDeclaredConstructor(cArg).newInstance(data));
            
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException | NullPointerException |SecurityException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return convertedData;
    }
    
}
