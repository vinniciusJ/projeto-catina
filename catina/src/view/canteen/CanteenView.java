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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import main.Environment;
import models.Canteen;
import models.Item;
import models.ItemGroup;
import view.View;
import view.canteen.popups.SalePopup;
import view.canteen.popups.ViewProfitPopup;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenView extends JFrame implements View{
    private final CanteenMenu menu;
    private final Canteen canteen;
    private CanteenTable itemsTable;
    List<Item> itens;    
    List<ItemGroup> groupsInCanteen;
    private Popup currentPopup;
    
    private Consumer<HashMap<String, Object>> onRegisterSale, onRegisterItem, onEdit;
    private Consumer onViewProfit;
    
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
            var selectedRow = getSelectedRow();
            
            if(selectedRow < 0){
                showNoneItemSelectedMessage();
            }
            else{
                var selectedItem = itens.get(selectedRow);
                
                showEditItemPopUp(onEdit, selectedItem);
            }
        }  
    }
    
    private class ViewProfitHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showViewProfitPopup(onViewProfit);
        } 
    }
    
    public CanteenView(Canteen canteen, List <Item> itens, List<ItemGroup> groupsInCanteen){        
        this.canteen = canteen;   
        this.itens = itens;
        this.groupsInCanteen = groupsInCanteen;
        this.menu = new CanteenMenu();
        this.itemsTable = new CanteenTable(groupsInCanteen);
        
        this.currentPopup = null;        
        this.init();
        this.paint();
    }
    
    public int getSelectedRow(){
        return this.itemsTable.getSelectedRow();
    }
    
    public void syncItems(List<Item> items){
        this.itemsTable = new CanteenTable(items);
        this.paint();
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
        this.showPopup(new SalePopup("Cadastrar venda", new Dimension(500, 280), this.itens), onSubmit);
    }
    
    public void showRegisterItemPopup(Consumer onSubmit){
        this.showPopup(new ItemPopup("Cadastrar Item", new Dimension(500, 280)), onSubmit);
    }

    public void showEditItemPopUp(Consumer onSubmit, Item data){ 
        this.showPopup(new ItemPopup("Editar Item", new Dimension(500, 280), data), onSubmit);
    }
    
    public void showViewProfitPopup(Consumer onSubmit){
        this.showPopup(new ViewProfitPopup("Visualizar lucro", new Dimension(500, 280)), onSubmit);
    }
    
    
    @Override
    public void init(){ 
        this.setSize(1080, 720); 
        this.setResizable(false);
        this.setTitle(Environment.getCurrentCanteen().getName() + " - CãTina");
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

    public List<ItemGroup> getGroupsInCanteen() {
        return groupsInCanteen;
    }

    public void setGroupsInCanteen(List<ItemGroup> groupsInCanteen) {
        this.groupsInCanteen = groupsInCanteen;
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
