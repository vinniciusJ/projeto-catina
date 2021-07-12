package controllers;

import dao.DAO;
import java.util.ArrayList;
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
    

    @Override
    public void init() {
        System.out.println("Oi");
    }

}
