package models;

import java.util.Locale;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dyogo
 */
public class Canteen extends ModelStandart{    
    private String name;
    private double balance;            
    
    public Canteen(JSONObject fields){
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");                        
        this.balance = (double) fields.get("balance"); 
    }
    
    public Canteen(){}

    public Canteen(String name){
        this.name = name;
        this.balance = 0;
        this.setId();
    }
    
    public Canteen(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String databaseName() {
        return "Canteen";
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toJSONString() {
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"name\": \"%s\", \"balance\": %.2f}", this.id, this.name, this.balance);        
        return s;
    }           
}
