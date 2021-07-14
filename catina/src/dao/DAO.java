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
import models.ModelStandart;
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
        
    public void delete(ModelStandart object) {
        String data = object.toJSONString();
        
        this.connection.delete(data);        
    }
    
    public ArrayList<ModelStandart> get() {             
        
        JSONArray JSONData = this.connection.read();             
        ArrayList<ModelStandart> dataObjects = new ArrayList();        
        
        JSONData.forEach((data) -> {                     
            dataObjects.add(this.parseData((JSONObject)data));
            
        });        
        return dataObjects;
    }
    
    public ModelStandart get(String id){
        var data = this.get();        
        ModelStandart object = null;
       
        var iterator = data.iterator();
        
        while(iterator.hasNext()){
            var datum = iterator.next();                                    
            if(datum.getId().equals(id)){
                object = datum;
            }
        }
        return object;
    }
    
    public void post(ModelStandart object) {        
        this.connection.write(object.toJSONString());
    }
    
    public void put(ModelStandart object) {
        this.connection.replace(object.getId(), object.toJSONString());
    }
    
    private ModelStandart parseData(JSONObject data){          
        
        Class [] cArg = new Class[1];
        cArg[0] = JSONObject.class;
                        
        ModelStandart convertedData = null;
        try {     
            convertedData = (ModelStandart) this.classIdentifier.cast(
            this.classIdentifier.getDeclaredConstructor(cArg).newInstance(data));
            
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException | NullPointerException |SecurityException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return convertedData;
    }
    
}
