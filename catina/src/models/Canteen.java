package models;

import dao.DAO;
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
    String id;
    String name;
    double balance;        
    Manager manager;
    
    public Canteen(JSONObject fields){
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        
        String managerId = (String) fields.get("managerId");
        var managerDAO = new DAO(Manager.class);
        this.manager = (Manager) managerDAO.get(managerId);
        
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
    public String getManagerId() {
        return manager.id;
    }

    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(String managerId) {
        this.manager.id = managerId;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String getId() {
        return this.id;
    }
    
    
}
