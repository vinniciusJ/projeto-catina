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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import models.CanteenItem;

/**
 *
 * @author Vinicius Jimenez
 */
public final class ItemPopup extends Popup{
    private final JTextField nameInput;
    private final JSpinner priceInput, qttyInput;
    private final boolean isEditing;
    private long canteenId;
    
    public ItemPopup(String title, Dimension dimension){
        super(title, dimension);
        
        this.isEditing = false;
           
        this.nameInput = new JTextField();
        this.priceInput = new JSpinner(new SpinnerNumberModel(0, 0, 100.0, 0.1));
        this.qttyInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
       
        this.init();
    }
    
    public ItemPopup(String title, Dimension dimension, CanteenItem values){
        super(title, dimension);
        
        this.canteenId = (long) values.getCanteen().getId();
        this.isEditing = true;
           
        this.nameInput = new JTextField();
        this.priceInput = new JSpinner(new SpinnerNumberModel(0, 0, 100.0, 0.1));
        this.qttyInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        
        this.nameInput.setText(values.getItem().getName());
        this.priceInput.setValue(values.getItem().getPrice());
        this.qttyInput.setValue(values.getQuantity());
       
        this.init();
    }
    
    private class SaveItemHandler implements ActionListener{
        private final Consumer<HashMap<String, Object>> callback;
        
        SaveItemHandler(Consumer<HashMap<String, Object>> callback){
            this.callback = callback;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            var args = new HashMap<String, Object>(){{
                put("name", nameInput.getText());
                put("price", priceInput.getValue());
                put("qtty", qttyInput.getValue());
            }};
            
            if(isEditing){
                args.put("canteenId", canteenId);
            }
            
            this.callback.accept(args);
            
            closePopup();
        }
    }
    
    private class CancelOperationHandler implements ActionListener{
        private final Supplier callback;
        
        CancelOperationHandler(Supplier callback){
            this.callback = callback;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            callback.get();
            closePopup();
        }
    }

    
    public void init(){
        var popupPanel = new JPanel();
        
        var formContainer = new JPanel();
        var optionsContainer = new JPanel();
        
        this.nameInput.setSize(200, 100);
        
        var nameInputContainer = this.createInputContainer("Nome do Produto: ", this.nameInput);
        var priceInputContainer = this.createInputContainer("Preço: ", this.priceInput);
        var qttyInputContainer = this.createInputContainer("Quantidade: ", this.qttyInput);
        
        var defaultButtonBorder = this.saveButton.getBorder();
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
        
        nameInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        priceInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        formContainer.setBorder(new EmptyBorder(20, 0, 20, 0));
        formContainer.setLayout(new GridBagLayout());
       
        this.setComponentInHorizontalGrid(formContainer, nameInputContainer, 0, 0, 2);
        this.setComponentInHorizontalGrid(formContainer, priceInputContainer, 2, 0, 1);
        this.setComponentInHorizontalGrid(formContainer, qttyInputContainer, 3, 0, 1);
        
        this.saveButton.setFont(buttonsFonts);
        this.closeButton.setFont(buttonsFonts);
        
        this.saveButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 25, 10, 25)));
        this.closeButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 15, 10, 15)));
        
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
        this.saveButton.addActionListener(new SaveItemHandler(callback));
    }

    @Override
    public void onCancel(Supplier callback) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                callback.get();
            }
        });
        
        this.closeButton.addActionListener(new CancelOperationHandler(callback));
    }
    /**
     * @return the nameInput
     */
    public JTextField getNameInput() {
        return nameInput;
    }

    /**
     * @return the priceInput
     */
    public JSpinner getPriceInput() {
        return priceInput;
    }

    /**
     * @return the qttyInput
     */
    public JSpinner getQttyInput() {
        return qttyInput;
    }

}
