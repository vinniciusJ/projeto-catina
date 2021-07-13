/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import controllers.CanteenController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.Item;

/**
 *
 * @author Vinicius Jimenez
 */
public final class SalePopup extends Popup{
    private final List<Item> options;
    private final JComboBox<String> selectInput;
    private final JSpinner quantityInput;
    private final JLabel estimatedValue;
    private Item selectedItem;
    private int selectedQtty;
 
    public SalePopup(String title, Dimension dimension, List<Item> options) {
        super(title, dimension);

        this.options = options;
                 
        var castedOptions = new String[this.options.size() + 1];
        
        castedOptions[0] = "Selecione um item";
        
        for(int i = 1; i < castedOptions.length; i++){
            castedOptions[i] = this.options.get(i).getName();
        }

        this.selectedItem = null;
        this.selectedQtty = 0;
        
        this.estimatedValue = new JLabel();
        this.selectInput = new JComboBox<>(castedOptions);
        this.quantityInput = new JSpinner();
        
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
            var args = new HashMap<String, Object>(){{
                put("item", selectedItem);
                put("qtty", selectedQtty);
            }};

            this.callback.accept(args);
        }
    }
    
    private class SelectInputHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            var selectedItemId = selectInput.getSelectedIndex();
            
            if(selectedItemId != 0){
                var item = (Item) options.get(selectedItemId - 1);
                var canteenId = item.getCanteen().getId();
                
                var maximum = CanteenController.calculateQuantityOfItemInCanteen(item.getName(), canteenId);
                
                selectedItem = item;
                quantityInput.setModel(new SpinnerNumberModel(0, 0, maximum, 1));
            }
            else{
                selectedItem = null;
                
                quantityInput.setValue(0);
                quantityInput.setEnabled(false);
            }
        } 
    }
    
    private class EstimatedValueHandler implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            if(selectedItem != null){
                selectedQtty = (int) quantityInput.getValue();
            }
        }  
    }
    
    public void init(){
        var defaultButtonBorder = this.saveButton.getBorder();
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
        
        this.quantityInput.setEnabled(false);
        
        this.estimatedValue.setText("R$ 00,00");
        this.estimatedValue.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        
        this.saveButton.setFont(buttonsFonts);
        this.closeButton.setFont(buttonsFonts);
        
        this.saveButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 25, 10, 25)));
        this.closeButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 15, 10, 15))); 
        
        this.selectInput.addActionListener(new SelectInputHandler());
        this.quantityInput.addChangeListener(new EstimatedValueHandler());

    }
    
    public void paint(){
        var popupPanel = new JPanel();
        
        var formContainer = new JPanel();
        var optionsContainer = new JPanel(); 
        
        var selectInputContainer = this.createInputContainer("Nome do produto: ", this.selectInput);
        var qttyInputContainer = this.createInputContainer("Quantidade: ", this.quantityInput);
        var estimatedValueContainer = this.createInputContainer("Valor: ", this.estimatedValue);
        
        selectInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        qttyInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        formContainer.setBorder(new EmptyBorder(20, 0, 20, 0));
        formContainer.setLayout(new GridBagLayout());
        
        this.setComponentInHorizontalGrid(formContainer, selectInputContainer, 0, 0, 2);
        this.setComponentInHorizontalGrid(formContainer, qttyInputContainer, 2, 0, 1);
        this.setComponentInHorizontalGrid(formContainer, estimatedValueContainer, 3, 0, 1);
        
        optionsContainer.setBorder(new EmptyBorder(20, 0, 20, 0));  
        
        optionsContainer.add(this.saveButton);
        optionsContainer.add(this.closeButton);
        
        optionsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        popupPanel.add(formContainer);
        popupPanel.add(optionsContainer);
        
        this.addChildren(popupPanel);
    }
   

    @Override
    public void onSave(Consumer<HashMap<String, Object>> callback) {
        this.saveButton.addActionListener(new SaveSaleHandler(callback));
    }
    
}
