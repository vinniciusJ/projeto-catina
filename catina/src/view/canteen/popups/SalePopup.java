/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Item;

/**
 *
 * @author Vinicius Jimenez
 */
public final class SalePopup extends Popup{
    private final JButton addSoldItem;
    private final List<Item> items;
    private final List<SaleInputContainer> itemsInputs;
 
    public SalePopup(String title, Dimension dimension, List<Item> options) {
        super(title, dimension);
       
        
        this.items = options;
        
        this.addSoldItem = new JButton();
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

        }
    }
    
    
    public void init(){
        var defaultButtonBorder = this.saveButton.getBorder();
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
     
        this.itemsInputs.add(new SaleInputContainer(this.items));
        this.itemsInputs.add(new SaleInputContainer(this.items));
        

        this.saveButton.setFont(buttonsFonts);
        this.closeButton.setFont(buttonsFonts);
        
        this.saveButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 25, 10, 25)));
        this.closeButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 15, 10, 15))); 
    }
    
    public void paint(){
        var popupPanel = new JPanel();
        var inputsContainer = new JPanel();
        var optionsContainer = new JPanel(); 
        
        inputsContainer.setLayout(new GridLayout(this.itemsInputs.size(), 1));
        
        optionsContainer.setBorder(new EmptyBorder(20, 0, 20, 0));  
        optionsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        
        optionsContainer.add(this.saveButton);
        optionsContainer.add(this.closeButton);
        
        this.itemsInputs.forEach(input -> inputsContainer.add(input));
        
        popupPanel.add(inputsContainer);
        popupPanel.add(optionsContainer);
        
        this.addChildren(popupPanel);
    }
   
    public void showWarningMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Editar Item", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onSave(Consumer<HashMap<String, Object>> callback) {
        this.saveButton.addActionListener(new SaveSaleHandler(callback));
    }
    
}
