package controllers;

import dao.DAO;
import java.util.ArrayList;
import java.util.stream.Collectors;
import main.Environment;
import models.Canteen;
import models.Item;
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
        this.itemDAO = new DAO(Item.class);        
        this.canteenDAO = new DAO(Canteen.class);           
                    
        var manager = Environment.getUser();            
        var canteen = manager.getCanteen(); 
        
        Environment.setCurrentCanteen(canteen);        
                
        var items = (ArrayList) this.itemDAO.get()
                .stream()
                .map(item -> (Item) item)
                .filter(item -> item.getCanteen().getId().equals(canteen.getId()))
                .collect(Collectors.toList());                         
        
        this.view = new CanteenView(canteen, items);
    }
    
    public void registerItem(String name, double price, int qtty){
        System.out.println(name);
        System.out.println(price);
        System.out.println(qtty);
        
        this.view.syncItems(new ArrayList<>());
    }
    
    public void editItem(Item canteenItem, String name, double price, int qtty){
        System.out.println(canteenItem);
        
        System.out.println(name);
        System.out.println(price);
        System.out.println(qtty);  
   }
    
    public void saleItem(Item item, int qtty){
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
            
            this.registerItem(name, price, qtty);
        });
        
        this.view.setOnEdit(data -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var item = (Item) data.get("canteenId");
            
            this.editItem(item, name, price, qtty);
        });
        
        this.view.setOnRegisterSale(data -> {
            var item = (Item) data.get("item");
            var qtty = (Integer) data.get("qtty");
            
            this.saleItem(item, qtty);
        });
        
        this.view.setOnViewProfit(data -> this.viewProfit());
    }    
    
    public static int calculateQuantityOfItemInCanteen(String itemName, String canteenId){
        int counter = 0;
        
        var dao = new DAO(Item.class);
                        
        counter = dao.get().stream().map((item) -> (Item) item).filter((castedItem) -> (castedItem.getCanteen().getId().equals(canteenId) && castedItem.getName().equals(itemName))).map((_item) -> 1).reduce(counter, Integer::sum);
        
        return counter;
    }

}
