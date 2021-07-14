package models;

import dao.DAO;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
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
    String id;
    String name;
    double price;
    String type;
    Canteen canteen;
    LocalDate purchaseDate;
    LocalDate saleDate;
    
    public Item(JSONObject fields){
        String canteenId = (String) fields.get("canteenId");
        var canteenDAO = new DAO(Canteen.class);        
        
        this.canteen =  (Canteen) canteenDAO.get(canteenId);        
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.price = (double) fields.get("price");
        this.type = (String) fields.get("type");        
        this.purchaseDate = LocalDate.parse(((String) fields.get("purchaseDate")));        
        this.saleDate = LocalDate.parse(((String) fields.get("saleDate")));                
        
    }
    
    public Item (String name, double price, String type, String canteenId){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.type = type;
        var canteenDAO = new DAO(Canteen.class);
        this.canteen =  (Canteen) canteenDAO.get(canteenId);                
        
        this.purchaseDate = LocalDate.now();
        this.saleDate = LocalDate.now();
    }

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", canteen=" + canteen + ", purchaseDate=" + purchaseDate + ", saleDate=" + saleDate + '}';
    }
    
    
    public String toJSONString(){
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"canteenId\": \"%s\", \"name\": \"%s\", \"price\": %.2f, \"type\": \"%s\", \"purchaseDate\": \"%s\", \"saleDate\": \"%s\"}", this.id, this.canteen.getId(), this.name, this.price, this.type, this.purchaseDate.toString(), this.saleDate.toString());        
        return s;
    }
                   
}
