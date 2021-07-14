package models;

import dao.DAO;
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
public class Item extends ModelStandart{    
    String name;
    double price;
    long quantity;
    String type;
    Canteen canteen;    
    
    public Item(JSONObject fields){
        String canteenId = (String) fields.get("canteenId");
        var canteenDAO = new DAO(Canteen.class);                
        this.canteen =  (Canteen) canteenDAO.get(canteenId);        
        
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.price = (double) fields.get("price");
        this.type = (String) fields.get("type");        
        this.quantity = (long) fields.get("quantity");        

    }
    
    public Item (String name, double price, String type, String canteenId, long quantity){
        this.setId();
        this.name = name;
        this.price = price;
        this.type = type;
        var canteenDAO = new DAO(Canteen.class);
        this.canteen =  (Canteen) canteenDAO.get(canteenId);                
        this.quantity = quantity;
    }

    public Canteen getCanteen() {
        return canteen;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public void setPrice(double price) {
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
    public String toJSONString(){
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"canteenId\": \"%s\", \"name\": \"%s\", \"price\": %.2f, \"type\": \"%s\", \"quantity\": \"%s\"}", this.id, this.canteen.getId(), this.name, this.price, this.type, this.quantity);        
        return s;
    }
                   
}
