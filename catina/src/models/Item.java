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
public class Item implements ModelDatabase{
    long id;
    String name;
    double price;
    String type;
    
    public Item(JSONObject fields){
        this.id = (long) fields.get("id");
        this.name = (String) fields.get("name");
        this.price = (double) fields.get("price");
        this.type = (String) fields.get("type");
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }
    
    public Item(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String databaseName() {
        return "Item";
    }
    
    
}
