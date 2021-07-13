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
public class CanteenItem implements ModelDatabase{
    private long id, quantity;
    private Canteen canteen;
    private Item item;
    
    public CanteenItem(JSONObject fields){
        var canteenId = (long) fields.get("canteenId");
        var itemId = (long) fields.get("itemId");
        
        var canteenDAO = new DAO(Canteen.class);
        var itemDAO = new DAO(Item.class);

        this.id = (long) fields.get("id");
        this.item = (Item) itemDAO.get(itemId); 
        this.canteen =  (Canteen) canteenDAO.get(canteenId);
        this.quantity = (long) fields.get("quantity");

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
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

    @Override
    public long getId() {
        return this.id;
    }
    
}
