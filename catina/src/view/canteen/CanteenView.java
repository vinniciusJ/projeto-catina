/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import main.Environment;
import models.CanteenItem;
import view.View;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenView extends JFrame implements View{
    private final CanteenMenu menu;
    private CanteenItemList itemList;
    
    public CanteenView(){
        this.menu = new CanteenMenu();
        this.itemList = new CanteenItemList();
        
        this.init();
        this.paint();
    }
    
    public void setCanteenItems(ArrayList<CanteenItem> items){
        this.itemList = new CanteenItemList(items);
    }
    
    public void onRegisterSale(ActionListener handler){
        this.menu.addRegisterSaleEventHandler(handler);
    }
    
    public void onRegisterItem(ActionListener handler){
        this.menu.addRegisterItemEventHandler(handler);
    }
    
    public void onViewProfit(ActionListener handler){
        this.menu.addViewProfitEventHandler(handler);
    }
    
    public void onEditItem(ActionListener handler){
        this.menu.addEditItemEventHandler(handler);
    }
    
    @Override
    public void init(){ 
        this.setSize(1080, 720); 
        this.setResizable(false);
        this.setTitle("CaTina - Home");
        this.setLocationRelativeTo(null);
        this.setIconImage(Environment.LOGO_ICON.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint() {
        var scrollItemList = new JScrollPane(this.itemList);
        
        
        this.add(this.menu, BorderLayout.NORTH);
        this.add(scrollItemList, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
}
