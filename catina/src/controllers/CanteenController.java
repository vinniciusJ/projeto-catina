package controllers;

import dao.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import main.Environment;
import models.Canteen;
import models.Item;
import models.ItemGroup;
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
                
        var dataItems = (ArrayList) this.itemDAO.get().stream().map(item -> (Item) item).collect(Collectors.toList());            
        ArrayList groupsInCanteen = CanteenController.createItensGroups(dataItems);       
                          
        this.view = new CanteenView(canteen, groupsInCanteen);
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
    
    public static ArrayList <ItemGroup> createItensGroups(List <Item> itens){
        var groupsInCanteen = new ArrayList<ItemGroup>();                     
        var iterator = itens.iterator();
        var canteen = Environment.getCurrentCanteen();
        System.out.println("banana");
        while(iterator.hasNext()){
            var item = iterator.next();               
            var itemCanteenId = (String) item.getCanteen().getId();            
            System.out.println("maça");
            if (itemCanteenId.equals(canteen.getId())){  
                System.out.println("teste");
                var groupInCanteen = new ItemGroup(item.getName(), item.getPrice(), item.getType(), item);                     
                boolean inGroups = false;
                System.out.println("abacai");
                
                for(ItemGroup group : groupsInCanteen){
                    if (group.equals(groupInCanteen)){
                        group.increaseQuantity();
                        group.addItem(item);
                        inGroups = true;
                    }
                    System.out.println("melancia");
                }
                if (!inGroups){
                    groupInCanteen.setQuantity(1);
                    groupsInCanteen.add(groupInCanteen);
                }                                     
                System.out.println("cabo");
            }                        
            
        }
        
        return groupsInCanteen;
    }
    
    public static ArrayList<Item> getItemsFromGroups(List <ItemGroup> itemsGroups){
        ArrayList<Item> items = new ArrayList <>();
        itemsGroups.forEach(group -> {
            var groupItems = group.getItems();
            groupItems.forEach(item -> {
                items.add(item);
            });
        });
        return items;
    }
    
}
