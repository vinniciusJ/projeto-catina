/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
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
    private CanteenTable itemsTable;
    private List<CanteenItem> items;
    
    public CanteenView(List<CanteenItem> items){
        this.items = items;
        
        this.menu = new CanteenMenu();
        this.itemsTable = new CanteenTable(items);
        
        this.init();
        this.paint();
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
    
    public int getSelectedRow(){
        return this.itemsTable.getSelectedRow();
    }
    
    public void syncItems(List<CanteenItem> items){
        this.itemsTable = new CanteenTable(items);
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
        var scrollPanel = new JScrollPane(this.itemsTable);
        
        this.add(this.menu, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }

    /**
     * @return the items
     */
    public List<CanteenItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<CanteenItem> items) {
        this.items = items;
    }
}
