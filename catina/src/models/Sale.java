/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.DAO;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import org.json.simple.JSONObject;

/**
 *
 * @author Dyogo
 */
public final class Sale extends ModelStandart{
    LocalDate date;
    Canteen canteen;
    double totalCost;
    
    public Sale (JSONObject fields){
        this.id = (String) fields.get("id");        
        this.date = LocalDate.parse(fields.get("date").toString());        
        String canteenId = (String) fields.get("canteenId");        
        this.setCanteen(canteenId);
    }
    
    public Sale (String canteenId){
        this.setId();
        this.setCanteen(canteenId);
        this.date = LocalDate.now();
    }
    
    public Sale(){} 
    public void setCanteen(String canteenId){
        var canteenDAO = new DAO(Canteen.class);
        this.canteen = (Canteen) canteenDAO.get(canteenId);               
    }
    
    public void setTotalCost(HashMap<ItemOnSale, Integer> itemsQtty){
        double cost = 0;
        cost = itemsQtty
                .keySet()
                .stream()
                .map((item) -> (item.price) * itemsQtty.get(item))
                .reduce(cost, (accumulator, _item) -> accumulator + _item);
        this.totalCost = cost;
    }
    
    
    @Override
    public String databaseName() {
        return "sale";
    }

    @Override
    public String toJSONString() {
         String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"canteenId\": \"%s\", \"totalCost\": \"%.2f\", \"date\": \"%s\"}",
                 this.id, this.canteen.getId(), this.totalCost, this.date.toString());        
        return s;
    }
    
}