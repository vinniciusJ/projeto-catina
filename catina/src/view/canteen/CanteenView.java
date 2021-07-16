/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import view.canteen.popups.Popup;
import view.canteen.popups.ItemPopup;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import main.Environment;
import models.Canteen;
import models.ItemOnSale;
import view.ClickEventHandler;
import view.canteen.popups.SalePopup;
import view.ComponentInterface;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenView extends JFrame implements ComponentInterface{
    private final CanteenMenu menu;
    private final Canteen canteen;
    private CanteenTable itemsTable;
    private List<ItemOnSale> items;
    private Popup currentPopup;
   
    private class RegisterSaleHandler extends ClickEventHandler{
        RegisterSaleHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            showRegisterSalePopup(this.callback);
        }
    }
    
    private class RegisterItemHandler extends ClickEventHandler{
        RegisterItemHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            showRegisterItemPopup(this.callback);
        }
    }
    
    private class EditItemHandler extends ClickEventHandler{
        EditItemHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            var selectedRow = getSelectedRow();
            
            System.out.println(selectedRow);
            if(selectedRow < 0){
                showNoneItemSelectedMessage();
            }
            else{
                var selectedGroup = items.get(selectedRow);

                showEditGroupPopUp(this.callback, selectedGroup);
            }
        }  
    }
    
    private class ViewProfitHandler extends ClickEventHandler{
        ViewProfitHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            showViewProfitPopup(this.callback);
        } 
    }
    
    public CanteenView(Canteen canteen, List<ItemOnSale> items){        
        this.items = items;
        this.canteen = canteen;
        
        this.menu = new CanteenMenu();
        this.itemsTable = new CanteenTable(items);
        
        this.currentPopup = null;

        
        this.init();
        this.paint();
    }
       
    public int getSelectedRow(){
        System.out.println(itemsTable.getRowCount());
        return this.itemsTable.getSelectedRow();        
    }
    
    public void syncItems(List<ItemOnSale> items){
        this.items = items;   
        this.itemsTable = new CanteenTable(items);        
    }
    
    // Factory Pattern
    
    public void showPopup(Popup popup, Consumer onSubmit){
        if(this.currentPopup == null){
            this.currentPopup = popup;
            
            this.currentPopup.onSave(data -> {
                this.currentPopup = null;
                
                onSubmit.accept(data);
            });
            
            this.currentPopup.onCancel(() -> this.currentPopup = null);
            this.currentPopup.showPopup();
        }
    }  
    
    public void showNoneItemSelectedMessage(){
        JOptionPane.showMessageDialog(this, "Por favor, selecione um item para editar", "Editar Item", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showRegisterSalePopup(Consumer onSubmit){        
        this.showPopup(new SalePopup("Cadastrar venda", new Dimension(540, 360), this.items), onSubmit);
    }
    
    public void showRegisterItemPopup(Consumer onSubmit){
        this.showPopup(new ItemPopup("Cadastrar Item", new Dimension(580, 280)), onSubmit);
    }

    public void showEditGroupPopUp(Consumer onSubmit, ItemOnSale data){         
        this.showPopup(new ItemPopup("Editar Item", new Dimension(600, 280), data), onSubmit);
    }
    
    public void showViewProfitPopup(Consumer onSubmit){
       onSubmit.accept(null);
    }
    
    public void setSaleRegisterHandler(Consumer<HashMap<String, Object>> callback){
        this.menu.addRegisterSaleEventHandler(new RegisterSaleHandler(callback));
    }
    
    public void setRegisterItemHandler(Consumer<HashMap<String, Object>> callback){
        this.menu.addRegisterItemEventHandler(new RegisterItemHandler(callback));
    }
    public void setEditItemHandler(Consumer<HashMap<String, Object>> callback){
        this.menu.addEditItemEventHandler(new EditItemHandler(callback));
    }
    
    public void setViewProfitHandler(Consumer callback){
        this.menu.addViewProfitEventHandler(new ViewProfitHandler(callback));
    }
    
    
    @Override
    public void init(){ 
        this.setSize(1080, 720); 
        this.setResizable(false);
        this.setTitle(Environment.getCurrentCanteen().getName() + " - CãTina");
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

    public List<ItemOnSale> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */

    public void setItems(List<ItemOnSale> items) {
        this.items = items;
    }
}
