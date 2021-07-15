/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.DAO;
import java.util.Locale;
import org.json.simple.JSONObject;

/**
 *
 * @author Dyogo
 */
public final class SoldItem extends ModelStandart{
    Sale sale;
    long qtty;
    double unitaryPrice;
    double totalCost;
    
    
    public SoldItem(JSONObject fields){
        this.id = (String) fields.get("id");        
        this.qtty = (long) fields.get("qtty");        
        this.unitaryPrice = (double) fields.get("unitaryPrice");
        this.totalCost = (double) fields.get("totalCost");
        
        String saleId = (String) fields.get("saleId");        
        this.setSale(saleId);
    }
    
    public SoldItem(ItemOnSale itemSold, int qtty, Sale sale){
        this.setId();
        this.qtty = qtty;
        this.unitaryPrice = itemSold.getPrice();
        this.totalCost = this.unitaryPrice * this.qtty;
        this.sale = sale;
    }
    
    public SoldItem(){}
    
    public void setSale(String saleId){
        var saleDAO = new DAO(Sale.class);
        this.sale = (Sale) saleDAO.get(saleId);   
    }
    
    @Override
    public String databaseName() {
        return "soldItem";
    }

    @Override
    public String toJSONString() {
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"saleId\": \"%s\", \"unitaryPrice\": \"%.2f\", \"totalCost\": \"%.2f\", \"qtty\": \"%s\"}", 
                this.id, this.sale.getId(), this.unitaryPrice, this.totalCost, this.qtty );        
        return s;
    }
    
}
