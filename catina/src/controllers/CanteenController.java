package controllers;

import dao.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import main.Environment;
import models.Canteen;
import models.Item;
import models.ItemGroup;
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
public class CanteenController implements AppController{
    private final DAO itemDAO;
    private final DAO canteenDAO;
    private final CanteenView view;
    
    public CanteenController(){
        this.itemDAO = new DAO(Item.class);        
        this.canteenDAO = new DAO(Canteen.class);           
        
        var groupsInCanteen = new ArrayList<ItemGroup>();        
        var itens = new ArrayList<Item>();        
        var manager = Environment.getUser();    
        
        var canteen = manager.getCanteen(); 
        
        var iterator = this.itemDAO.get().iterator();  
        
        while(iterator.hasNext()){
            var item = (Item) iterator.next();               
            var itemCanteenId = (String) item.getCanteen().getId();
            
            if (itemCanteenId.equals(canteen.getId())){         
                itens.add(item);
                var groupInCanteen = new ItemGroup(item.getName(), item.getPrice(), item.getType(), item);                      
                boolean inGroups = false;
                
                for(ItemGroup group : groupsInCanteen){
                    if (group.equals(groupInCanteen)){
                        group.increaseQuantity();
                        group.addItem(item);
                        inGroups = true;
                    }
                }
                if (!inGroups){
                    groupInCanteen.setQuantity(1);
                    groupsInCanteen.add(groupInCanteen);
                }                                     
            }                        
        }        
                        
        Environment.setCurrentCanteen(canteen);        
        this.view = new CanteenView(canteen, itens, groupsInCanteen);
    }
    
    public void registerItem(String name, double price, long qtty){
        System.out.println(name);
        System.out.println(price);
        System.out.println(qtty);
    }
    
    public void editItem(Item canteenItem, String name, double price, long qtty){
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

    @Override
    public void init() {
        this.view.setOnRegisterItem(data -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Long) data.get("qtty");
            
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
            var qtty = (int) data.get("qtty");
            
            this.saleItem(item, qtty);
        });
        
        this.view.setOnViewProfit(data -> this.viewProfit());
    }    
    
}
