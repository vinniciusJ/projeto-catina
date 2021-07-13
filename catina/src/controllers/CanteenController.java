package controllers;

import dao.DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import main.Environment;
import models.Canteen;
import models.Item;
import models.ItemGroupInCanteen;
import models.ModelDatabase;
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
        
        var itemsInCanteen = new ArrayList<Item>();        
        var groupsInCanteen = new ArrayList<ItemGroupInCanteen>();        
        var manager = Environment.getUSER();        
        Canteen canteen = manager.getCanteen();        
        var b = this.itemDAO.get();                
        var iterator = this.itemDAO.get().iterator();        
        while(iterator.hasNext()){
            var item = (Item) iterator.next();                                                    
            String itemCanteenId = item.getCanteen().getId();                
            if (itemCanteenId.equals(canteen.getId())){                                    
                itemsInCanteen.add(item);                                   
                var groupInCanteen = new ItemGroupInCanteen(item.getName(), item.getPrice());                
                if(!groupsInCanteen.contains(groupInCanteen)){
                    groupsInCanteen.add(groupInCanteen);
                }
            }                        
            
        }        
                        
        for(ItemGroupInCanteen group : groupsInCanteen){
            System.out.println(group.getName());
        }
        this.view = new CanteenView(canteen, itemsInCanteen);
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

    @Override
    public void init() {
        this.view.setOnRegisterItem((HashMap<String, Object> data) -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Long) data.get("qtty");
            
            this.registerItem(name, price, qtty);
        });
        
        this.view.setOnEdit((HashMap<String, Object> data) -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var item = (Item) data.get("canteenId");
            
            this.editItem(item, name, price, qtty);
        });

    }    
    
    public static int calculateQuantityOfItemInCanteen(String itemName, String canteenId){
        int counter = 0;
        
        var dao = new DAO(Item.class);
                        
        counter = dao.get().stream().map((item) -> (Item) item).filter((castedItem) -> (castedItem.getCanteen().getId().equals(canteenId) && castedItem.getName().equals(itemName))).map((_item) -> 1).reduce(counter, Integer::sum);
        
        return counter;
    }

}
