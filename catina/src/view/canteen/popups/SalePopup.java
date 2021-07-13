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
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.CanteenItem;

/**
 *
 * @author Vinicius Jimenez
 */
public final class SalePopup extends Popup{
    private final List<CanteenItem> options;
    private final JComboBox<String> selectInput;
    private final JSpinner quantityInput;
    private final JLabel estimatedValue;
 
    public SalePopup(String title, Dimension dimension, List<CanteenItem> options) {
        super(title, dimension);
        
        var castedOptions = new String[options.size()];
        
        for(int i = 0; i < castedOptions.length; i++){
            castedOptions[i] = options.get(i).getItem().getName();
        }
        
        this.options = options;
        
        System.out.println(castedOptions);
        
        this.estimatedValue = new JLabel("R$ 00,00");
        this.selectInput = new JComboBox<>(castedOptions);
        this.quantityInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        
        this.quantityInput.addChangeListener(new EstimatedValueHandler());
        
        this.init();
    }
    
    private class EstimatedValueHandler implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            System.out.println(selectInput.getItemAt(selectInput.getSelectedIndex()));
        }
        
    }
    
    public void init(){
        var popupPanel = new JPanel();
        
        var formContainer = new JPanel();
        var optionsContainer = new JPanel(); 
        
        var defaultButtonBorder = this.saveButton.getBorder();
        var buttonsFonts = new Font("Sans-Serif", Font.PLAIN, 16);
        
        var selectInputContainer = this.createInputContainer("Nome do produto: ", this.selectInput);
        var qttyInputContainer = this.createInputContainer("Quantidade: ", this.quantityInput);
        var estimatedValueContainer = this.createInputContainer("Valor: ", this.estimatedValue);
        
        this.estimatedValue.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        
        selectInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        qttyInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        formContainer.setBorder(new EmptyBorder(20, 0, 20, 0));
        formContainer.setLayout(new GridBagLayout());
        
        this.setComponentInHorizontalGrid(formContainer, selectInputContainer, 0, 0, 2);
        this.setComponentInHorizontalGrid(formContainer, qttyInputContainer, 2, 0, 1);
        this.setComponentInHorizontalGrid(formContainer, estimatedValueContainer, 3, 0, 1);

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
        
    }
    
}
