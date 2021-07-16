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
public class ItemOnSale extends Item{   
    private Canteen canteen;   
    
    public ItemOnSale(JSONObject fields){
        String canteenId = (String) fields.get("canteenId");
        var canteenDAO = new DAO(Canteen.class);                
        this.canteen =  (Canteen) canteenDAO.get(canteenId);        
        
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.price = (double) fields.get("price");
        this.type = (String) fields.get("type");        
        this.quantity = (long) fields.get("quantity");        

    }
    
    public ItemOnSale (String name, double price, String type, String canteenId, long quantity){
        this.setId();
        this.name = name;
        this.price = price;
        this.type = type;
        var canteenDAO = new DAO(Canteen.class);
        this.canteen =  (Canteen) canteenDAO.get(canteenId);                
        this.quantity = quantity;
    }
    
    public ItemOnSale(){}

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    @Override
    public String databaseName() {
        return "Item";
    }    
        
    @Override
    public String toJSONString(){
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"canteenId\": \"%s\", \"name\": \"%s\", \"price\": %.2f, \"type\": \"%s\", \"quantity\": %s}", this.id, this.canteen.getId(), this.name, this.price, this.type, this.quantity);        
        return s;
    }
                   
}
