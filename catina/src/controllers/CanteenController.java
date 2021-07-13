package controllers;

import dao.DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import main.Environment;
import models.Canteen;
import models.CanteenItem;
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
    private final DAO canteenItemDAO;
    private final CanteenView view;
    
    public CanteenController(){
        this.canteenItemDAO = new DAO(CanteenItem.class);
        
        var itemsInCanteen = new ArrayList<CanteenItem>();
        var managerId = Environment.getUSER().getId();
        
        Canteen canteen = null;
        
        var iterator = this.canteenItemDAO.get().iterator();
        
        while(iterator.hasNext()){
            var item = (CanteenItem) iterator.next();
            
            if(canteen == null){
                canteen = item.getCanteen();
            }
            
            if(item.getCanteen().getManagerId() == managerId){
                itemsInCanteen.add(item);
            }
        }
        
        this.view = new CanteenView(canteen, itemsInCanteen);
    }
    
    public void registerItem(String name, double price, long qtty){
        System.out.println(name);
        System.out.println(price);
        System.out.println(qtty);
    }
    
    public void editItem(CanteenItem canteenItem, String name, double price, long qtty){
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
            var canteenItem = (CanteenItem) data.get("canteenItem");
            
            this.editItem(canteenItem, name, price, qtty);
        });
    }

}
