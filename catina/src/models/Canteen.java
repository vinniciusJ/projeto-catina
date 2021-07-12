package models;

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
public class Canteen implements ModelDatabase{
    int id;
    String name;
    String responsible;
    float balance;        
    
    public Canteen(JSONObject fields){
        this.name = (String) fields.get("name");
        this.responsible = (String) fields.get("responsible");
        this.balance = (float) fields.get("balance");        
    }
    
    public Canteen(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String databaseName() {
        return "Canteen";
    }
    
    
}
