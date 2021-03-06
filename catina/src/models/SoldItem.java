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
public final class SoldItem extends Item{
    private Sale sale;    
    private double unitaryPrice;
    private double totalCost;    
    
    
    public SoldItem(JSONObject fields){        
        this.id = (String) fields.get("id");        
        this.quantity = (long) fields.get("qtty");        
        this.unitaryPrice = (double) fields.get("unitaryPrice");
        this.totalCost = (double) fields.get("totalCost");
        this.name = (String) fields.get("name");
        this.type = (String) fields.get("type");
        
        String saleId = (String) fields.get("saleId");        
        this.setSale(saleId);        
    }
    
    public SoldItem(ItemOnSale itemSold, int qtty, Sale sale){
        this.setId();
        this.quantity = qtty;
        this.unitaryPrice = itemSold.getPrice();
        this.totalCost = this.unitaryPrice * this.quantity;
        this.name = itemSold.getName();
        this.type = itemSold.getType();
        this.sale = sale;
    }
    
    public SoldItem(){}
    
    public void setSale(String saleId){
        var saleDAO = new DAO(Sale.class);
        this.sale = (Sale) saleDAO.get(saleId);   
    }
    
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String databaseName() {
        return "soldItem";
    }

    @Override
    public String toString() {
        return "SoldItem{" + "qtty=" + quantity + ", unitaryPrice=" + unitaryPrice + ", totalCost=" + totalCost + ", name=" + name + ", type=" + type + '}';
    }
    
    @Override
    public String toJSONString() {
        String s = String.format(Locale.ROOT, "{\"id\": \"%s\", \"saleId\": \"%s\", \"name\": \"%s\", \"type\": \"%s\", \"unitaryPrice\": %.2f, \"totalCost\": %.2f, \"qtty\": %s}", 
                this.id, this.sale.getId(), this.getName(), this.getType(), this.unitaryPrice, this.totalCost, this.quantity );        
        return s;
    }    
    
}
