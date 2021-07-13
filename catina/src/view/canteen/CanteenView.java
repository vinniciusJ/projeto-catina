/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import view.canteen.popups.Popup;
import view.canteen.popups.RegisterItemPopup;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import main.Environment;
import models.Canteen;
import models.CanteenItem;
import view.View;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenView extends JFrame implements View{
    private final CanteenMenu menu;
    private final Canteen canteen;
    private CanteenTable itemsTable;
    private List<CanteenItem> items;
    private Popup currentPopup;
    
    private Consumer<HashMap<String, Object>> onRegisterSale, onRegisterItem, onEdit;
    private Consumer<Void> onViewProfit;
    
    private class RegisterSaleHanlder implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showRegisterSalePopup(onRegisterSale);
        }
    }
    
    private class RegisterItemHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showRegisterItemPopup(onRegisterItem);
        }
    }
    
    private class EditItemHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showEditItemPopUp(onEdit);
        }  
    }
    
    private class ViewProfitHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showViewProfitPopup(onViewProfit);
        } 
    }
    
    public CanteenView(Canteen canteen, List<CanteenItem> items){
        this.items = items;
        this.canteen = canteen;
        
        this.menu = new CanteenMenu();
        this.itemsTable = new CanteenTable(items);
        
        this.currentPopup = null;
        
        this.init();
        this.paint();
    }
    
    public int getSelectedRow(){
        return this.itemsTable.getSelectedRow();
    }
    
    public void syncItems(List<CanteenItem> items){
        this.itemsTable = new CanteenTable(items);
    }
    
    public void showRegisterSalePopup(Consumer onSubmit){

    }
    
    public void showViewProfitPopup(Consumer onSubmit){
        
    }
    

    
    public void showRegisterItemPopup(Consumer<HashMap<String, Object>> onSubmit){
        if(this.currentPopup == null){
            this.currentPopup = new RegisterItemPopup("Cadastrar item", new Dimension(500, 280));
            
            this.currentPopup.onSave(data -> {
                this.currentPopup = null;
                
                onSubmit.accept(data);
            });
            
            this.currentPopup.onCancel(() -> this.currentPopup = null);
            this.currentPopup.showPopup();
        }
    }
    
    public void showEditItemPopUp(Consumer onSubmit){
        
    }
    
    
    @Override
    public void init(){ 
        this.setSize(1080, 720); 
        this.setResizable(false);
        this.setTitle("CaTina - Home");
        this.setLocationRelativeTo(null);
        this.setIconImage(Environment.LOGO_ICON.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.menu.addEditItemEventHandler(new EditItemHandler());
        this.menu.addRegisterItemEventHandler(new RegisterItemHandler());
        this.menu.addRegisterSaleEventHandler(new RegisterSaleHanlder());
        this.menu.addViewProfitEventHandler(new ViewProfitHandler());
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

    /**
     * @param onRegisterSale the onRegisterSale to set
     */
    public void setOnRegisterSale(Consumer<HashMap<String, Object>> onRegisterSale) {
        this.onRegisterSale = onRegisterSale;
    }

    /**
     * @param onRegisterItem the onRegisterItem to set
     */
    public void setOnRegisterItem(Consumer<HashMap<String, Object>> onRegisterItem) {
        this.onRegisterItem = onRegisterItem;
    }

    /**
     * @param onEdit the onEdit to set
     */
    public void setOnEdit(Consumer<HashMap<String, Object>> onEdit) {
        this.onEdit = onEdit;
    }

    /**
     * @param onViewProfit the onViewProfit to set
     */
    public void setOnViewProfit(Consumer<Void> onViewProfit) {
        this.onViewProfit = onViewProfit;
    }


}
