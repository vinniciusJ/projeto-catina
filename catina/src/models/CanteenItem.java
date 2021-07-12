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
public class CanteenItem implements modelDatabase{
    private Canteen canteen;
    private Item item;
    private int quantity;
    
    public CanteenItem(JSONObject fields){
        this.item = Item(fields.get("item"));        
        this.canteen = Canteen(fields.get("canteen"));        
        this.quantity = (int) fields.get("quantity");
    }
    
    public CanteenItem(){}

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String databaseName() {
        return "CanteenItem";
    }

    private Item Item(Object get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Canteen Canteen(Object get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
