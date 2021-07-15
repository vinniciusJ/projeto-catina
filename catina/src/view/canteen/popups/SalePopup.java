/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.border.EmptyBorder;

import models.ItemOnSale;


/**
 *
 * @author Vinicius Jimenez
 */
public final class SalePopup extends Popup{

    
    private final List<ItemOnSale> items;
    private final List<SaleInputContainer> itemsInputs;
    private final JPanel list;
    private final JButton addItemToSaleListButton;

 
    public SalePopup(String title, Dimension dimension, List<ItemOnSale> options) {
        super(title, dimension);
       
        this.items = options;
        
        this.addItemToSaleListButton = new JButton();
        this.list = new JPanel();
        this.itemsInputs = new ArrayList<>();
        
        this.init();
        this.paint();
    }
    
    private class SaveSaleHandler implements ActionListener{
        private final Consumer<HashMap<String, Object>> callback;
        
        SaveSaleHandler(Consumer<HashMap<String, Object>> callback){
            this.callback = callback;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            var items = new ArrayList<ItemOnSale>();
            var itemsQtty = new HashMap<ItemOnSale, Integer>();
            
            var isAllFieldsFilled = true;
            var iterator = itemsInputs.iterator();
            
            while(iterator.hasNext()){
                var input = (SaleInputContainer) iterator.next();

                
                if(!isFieldFilled(input)){
                    isAllFieldsFilled = false;
                }
                
                var selectedItem = (ItemOnSale) input.getSelectedItem();
                var qtty = (Integer) input.getSelectedQtty();
                
                items.add(selectedItem);
                itemsQtty.put(selectedItem, qtty);
            }
            
            
            if(!isAllFieldsFilled){
                showWarningMessage("Por favor, selecione um item e pelo menos uma unidade em todos os campos");
            }
            else{
                var args = new HashMap<String, Object>(){{
                    put("items", items);
                    put("itemsQtty", itemsQtty);
                }};
                
                closePopup();
                this.callback.accept(args);
            }
        }
        
        public boolean isFieldFilled(SaleInputContainer input){
            return !(input.getSelectedItem() == null || input.getSelectedQtty() == 0);
        }
    }
    
    private class AddItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addSaleInputContainer();
        }
    }
    
    public void init(){
        var defaultButtonBorder = this.saveButton.getBorder();
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
 
        this.addSaleInputContainer();
        
        this.list.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.list.setLayout(new GridLayout(1, 1));
        
        this.saveButton.setFont(buttonsFonts);
        this.closeButton.setFont(buttonsFonts);
        
        this.addItemToSaleListButton.setText("Adicionar item a lista");
        this.addItemToSaleListButton.setFont(buttonsFonts);
        this.addItemToSaleListButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(5, 25, 5, 25)));
        
        this.saveButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 25, 10, 25)));
        this.closeButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 15, 10, 15)));
        
        this.addItemToSaleListButton.addActionListener(new AddItemHandler());
    }
    
    public void paint(){
        var popupPanel = new JPanel();
        var optionsContainer = new JPanel(); 
        var addItemContainer = new JPanel();
        
        var scrollContainer = new JScrollPane(this.list);
        
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
        
        scrollContainer.setBorder(new EmptyBorder(10, 10, 10, 10));     
        scrollContainer.setPreferredSize(new Dimension(scrollContainer.getWidth(), 200));
        
        scrollContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
        scrollContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);            
        
        optionsContainer.setBorder(new EmptyBorder(20, 0, 20, 0));  
        optionsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        
        optionsContainer.add(this.saveButton);
        optionsContainer.add(this.closeButton);
        
        addItemContainer.add(this.addItemToSaleListButton);
     
        popupPanel.add(scrollContainer);
        popupPanel.add(addItemContainer);
        popupPanel.add(optionsContainer);
        
        this.addChildren(popupPanel);
    }
   
    public void addSaleInputContainer(){
        var itemInput = new SaleInputContainer(this.items);
        
        if(this.itemsInputs.size() == 0){
            itemInput.disableDeleteButton();
        }
        
        itemInput.setOnDelete(() -> {
            this.itemsInputs.remove(itemInput);
            this.list.remove(itemInput);
            
            return null;
        });
        
        this.itemsInputs.add(itemInput);

        this.list.add(itemInput);
        this.list.setLayout(new GridLayout(itemsInputs.size(), 1));
        this.list.revalidate();
    }
   
    
    public void showWarningMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Editar Item", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onSave(Consumer<HashMap<String, Object>> callback) {
        this.saveButton.addActionListener(new SaveSaleHandler(callback));
    }
}
