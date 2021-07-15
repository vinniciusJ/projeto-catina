package controllers;

import dao.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import main.Environment;
import models.Canteen;
import models.ItemOnSale;
import view.canteen.CanteenView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius Jimenez
 */
public class CanteenController{
    private final DAO itemDAO;
    private final DAO canteenDAO;
    private final CanteenView view;
    
    public CanteenController(){        
        this.itemDAO = new DAO(ItemOnSale.class);        
        this.canteenDAO = new DAO(Canteen.class);           
        
        var manager = Environment.getUser();            
        var canteen = manager.getCanteen(); 
        
        Environment.setCurrentCanteen(canteen);                
        var items = (ArrayList) 
                this.itemDAO
                .get()
                .stream()
                .map(item -> (ItemOnSale) item)
                .filter(item -> item.getCanteen().getId().equals(canteen.getId()))
                .collect(Collectors.toList());                         
        
        this.view = new CanteenView(canteen, items);
    }
    
    public void registerItem(String name, double price, int qtty, String type){
        var item = new ItemOnSale(name, price, type, Environment.getCurrentCanteen().getId(), qtty);        
        this.itemDAO.post(item);        
        
        /* this.view.syncItems(
                this.itemDAO
                        .get()
                        .stream()
                        .map(i -> (ItemOnSale) i)
                        .collect(Collectors.toList()));
        */
    }
    
    public void editItem(ItemOnSale canteenItem, String name, double price, int qtty){                                         
        canteenItem.setName(name);
        canteenItem.setPrice(price);
        canteenItem.setQuantity(qtty);
        
        this.itemDAO.put(canteenItem);           
   }
    
    public void registerSale(ItemOnSale item, int qtty){
        System.out.println(item);
        System.out.println(qtty);  
    }
    
    public void viewProfit(){
        
    }

    public void init() {
        this.view.setOnRegisterItem(data -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var type = (String) data.get("type");
            
            this.registerItem(name, price, qtty, type);
        });
        
        this.view.setOnEdit(data -> {
            System.out.println(data);
            var name = (String) data.get("name");
            var price = (double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var item = (ItemOnSale) data.get("canteenItem");
            
            this.editItem(item, name, price, qtty);
        });
        
        this.view.setOnRegisterSale(data -> {
            var items = (ArrayList<ItemOnSale>) data.get("items");
            var itemsQtty = (HashMap<ItemOnSale, Integer>) data.get("itemsQtty");
            
            // para acessar a qtty de um item -> itemsQtty.qtty(item); onde item é uma instancia de Item
            
            items.forEach(item -> {
                System.out.println(item.getName() + " " + itemsQtty.get(item));
            });
            
            //this.saleItem(item, qtty);
        });
        
        this.view.setOnViewProfit(data -> this.viewProfit());
    }        

}
