/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dyogo
 */
public class Connection {
    String filename;
    
    public Connection(String filename){
        this.filename = "src/database/" + filename + ".json";
    }
    
    public JSONArray read(){
        JSONParser jsonParser = new JSONParser();
        JSONArray list = null;
        try (FileReader reader = new FileReader(this.filename))
        {            
            Object obj = jsonParser.parse(reader);
            list = (JSONArray) obj;                            
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
        return list;                
    }
    
    public void write(JSONArray data){
        String s = "[\n";
        try (FileWriter file = new FileWriter(this.filename)) {   
            for(int i = 0; i < data.size(); i ++){
                var object = data.get(i);
                String objectString = object.toString();
                objectString = objectString.replace("{", "\t{\n\t\t");
                objectString = objectString.replaceAll(",", ",\n\t\t");
                
                if(i == data.size() - 1){
                    objectString = objectString.replace("}", "\n\t}\n");                    
                } else{
                    objectString = objectString.replace("}", "\n\t},\n");                    
                }
                s += objectString;
            }
            s += "]";               
            System.out.println(s);
            file.write(s); 
            file.flush();
 
        } catch (IOException e) {
            System.out.println(e);
        }        
    }
    public void write(String data){
        try {
            JSONArray previousData = this.read();
            JSONParser jsonParser = new JSONParser();          
            var object = jsonParser.parse(data);
            previousData.add(object);
            this.write(previousData);
        } catch (ParseException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }        
        
    
    public void replace (String oldObject, String newObject){                
        String fileContent = this.read().toJSONString();        
        String newFileContent = fileContent.replaceAll(oldObject, newObject);
        
        try (FileWriter file = new FileWriter(this.filename)) {            
            file.write(newFileContent); 
            file.flush();
 
        } catch (IOException e) {
            System.out.println(e);
        }        
    }
    
    public void delete (JSONObject object){
        String objectText = object.toJSONString();        
        String fileContent = this.read().toJSONString();       
        String newFileContent = fileContent.replaceAll(objectText, "");
        
        try (FileWriter file = new FileWriter(this.filename)) {            
            file.write(newFileContent); 
            file.flush();
 
        } catch (IOException e) {
            System.out.println(e);
        }        
    }
    
    public void delete (String objectText){        
        String fileContent = this.read().toJSONString();       
        String newFileContent = fileContent.replaceAll(objectText, "");
        
        try (FileWriter file = new FileWriter(this.filename)) {            
            file.write(newFileContent); 
            file.flush();
 
        } catch (IOException e) {
            System.out.println(e);
        }        
    }
    
}
