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
    long unitaryPrice;
    long totalCost;
    
    
    public SoldItem(JSONObject fields){
        this.id = (String) fields.get("id");        
        this.qtty = (long) fields.get("qtty");        
        this.unitaryPrice = (long) fields.get("unitaryPrice");
        this.totalCost = (long) fields.get("totalCost");
        
        String saleId = (String) fields.get("saleId");        
        this.setSale(saleId);
    }
    
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
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"saleId\": \"%s\", \"unitaryPrice\": \"%s\", \"totalCost\": \"%s\", \"qtty\": \"%s\"}", 
                this.id, this.sale.getId(), this.unitaryPrice, this.totalCost, this.qtty );        
        return s;
    }
    
}
