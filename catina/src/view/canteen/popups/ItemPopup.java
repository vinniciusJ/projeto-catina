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
import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import models.ItemOnSale;
import view.ClickEventHandler;

/**
 *
 * @author Vinicius Jimenez
 */
public final class ItemPopup extends Popup{
    private final JTextField nameInput, typeInput;
    private final JSpinner priceInput, qttyInput;
    private final boolean isEditing;

    private ItemOnSale item;

    public ItemPopup(String title, Dimension dimension){
        super(title, dimension);
        
        this.isEditing = false;
           
        this.nameInput = new JTextField();
        this.typeInput = new JTextField();
        this.priceInput = new JSpinner(new SpinnerNumberModel(0, 0, 100.0, 0.1));
        this.qttyInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
       
        this.init();
        this.paint();
    }
    
    public ItemPopup(String title, Dimension dimension, ItemOnSale item){
        super(title, dimension);
        
        this.item = item;

        this.isEditing = true;
           
        this.nameInput = new JTextField(item.getName());
        this.typeInput = new JTextField(item.getType());
        this.qttyInput = new JSpinner(new SpinnerNumberModel(item.getQuantity(), 0, 100, 1));
        this.priceInput = new JSpinner(new SpinnerNumberModel(item.getPrice(), 0, 100.0, 0.1));
 
        this.init();
        this.paint();
    }
    
    private class SaveItemHandler extends ClickEventHandler{
        SaveItemHandler(Consumer<HashMap<String, Object>> callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            double qttyInputDouble = (double) qttyInput.getValue();
            int qttyInputInt = (int) qttyInputDouble;            
            qttyInput.setValue(qttyInputInt);
            
            var args = new HashMap<String, Object>(){{
                put("name", nameInput.getText());
                put("price", priceInput.getValue());
                put("qtty", qttyInput.getValue());
                put("type", typeInput.getText());
            }};
           
            
            if(isEditing){
                args.put("canteenItem", item);
            }
            
            if(!this.isAllFieldsFilled()){
                showWarningMessage("Por favor, preencha todos os campos adequadamente");
            }
            else{            
                this.callback.accept(args);

                closePopup();
            }
        }
        
        public boolean isAnValidString(String string){
            return !(string.isBlank() || string.isEmpty());
        }
        
        public boolean isAllFieldsFilled(){
            var nameField = this.isAnValidString(nameInput.getText());
            var typeField = this.isAnValidString(typeInput.getText());
            var priceField = ((double) priceInput.getValue()) != 0;
            var qttyField = ((int) qttyInput.getValue()) != 0;  
            
            return nameField && typeField && priceField && qttyField;
        }
    }

    
    @Override
    public void init(){
        var defaultButtonBorder = this.saveButton.getBorder();
        var defaultInputPadding = BorderFactory.createCompoundBorder(this.nameInput.getBorder(), new EmptyBorder(3, 10, 3, 10));
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
                
        this.saveButton.setFont(buttonsFonts);
        this.closeButton.setFont(buttonsFonts);
        
        this.nameInput.setBorder(defaultInputPadding);
        this.typeInput.setBorder(defaultInputPadding);
        this.priceInput.setBorder(defaultInputPadding);
        this.qttyInput.setBorder(defaultInputPadding);
        
        this.saveButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 25, 10, 25)));
        this.closeButton.setBorder(BorderFactory.createCompoundBorder(defaultButtonBorder, new EmptyBorder(10, 15, 10, 15)));
    }
    
    
    @Override
    public void paint() {
        var popupPanel = new JPanel();
        var formContainer = new JPanel();
        var optionsContainer = new JPanel();
        
        var nameInputContainer = Popup.createInputContainer("Nome do Produto: ", this.nameInput);
        var typeInputContainer = Popup.createInputContainer("Tipo do Produto: ", this.typeInput);
        var priceInputContainer = Popup.createInputContainer("Preço: ", this.priceInput);
        var qttyInputContainer = Popup.createInputContainer("Quantidade: ", this.qttyInput);
        
        nameInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        typeInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        priceInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        formContainer.setBorder(new EmptyBorder(20, 0, 20, 0));
        formContainer.setLayout(new GridBagLayout());
       
        Popup.setComponentInHorizontalGrid(formContainer, nameInputContainer, 0, 0, 2);
        Popup.setComponentInHorizontalGrid(formContainer, typeInputContainer, 2, 0, 1);
        Popup.setComponentInHorizontalGrid(formContainer, priceInputContainer, 3, 0, 1);
        Popup.setComponentInHorizontalGrid(formContainer, qttyInputContainer, 4, 0, 1);
        
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
