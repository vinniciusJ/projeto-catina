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
    long id;
    long managerId;
    String name;
    double balance;        
    
    public Canteen(JSONObject fields){
        this.id = (long) fields.get("id");
        this.name = (String) fields.get("name");
        this.managerId = (long) fields.get("managerId");
        this.balance = (double) fields.get("balance"); 
    }
    
    public Canteen(){}

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

    /**
     * @return the managerId
     */
    public long getManagerId() {
        return managerId;
    }

    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public long getId() {
        return this.id;
    }
    
    
}
