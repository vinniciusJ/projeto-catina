/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public final class SaleInputContainer extends JPanel{
    private final List<Item> options;
    private final JComboBox<String> selectInput;
    private final JSpinner quantityInput;
    private final JLabel estimatedValue;
    private Item selectedItem;
    private int selectedQtty;
    
    public SaleInputContainer(List<Item> items){
        this.options = items;
        
        this.selectedItem = null;
        this.selectedQtty = 0;
        
        this.estimatedValue = new JLabel("R$ 00,00");
        this.selectInput = new JComboBox<>(this.createSelectOptions(items));
        this.quantityInput = new JSpinner();
                
        this.init();
        this.paint();
    }
    
    private class SelectInputHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            var selectedItemId = selectInput.getSelectedIndex();
            
            if(selectedItemId != 0){
                var item = (Item) options.get(selectedItemId - 1);
                var maximum = item.getQuantity();
                
                selectedItem = item;
                
                quantityInput.setEnabled(true);
                quantityInput.setModel(new SpinnerNumberModel(0, 0, maximum, 1));
            }
            else{
                selectedItem = null;
                
                estimatedValue.setText("R$ 00,00");
                quantityInput.setValue(0);
                quantityInput.setEnabled(false);
            }
        } 
    }
    
    private class EstimatedValueHandler implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            if(selectedItem != null){
                var decimalFormat = new DecimalFormat("#.00");
                var result = selectedItem.getPrice() * (int) quantityInput.getValue();
                
                estimatedValue.setText("R$ " + decimalFormat.format(result));
                selectedQtty = (int) quantityInput.getValue();
            }
        }  
    }
    
    public void init(){
        this.quantityInput.setEnabled(false);
        
        this.estimatedValue.setText("R$ 00,00");
        this.estimatedValue.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        
        this.selectInput.addActionListener(new SelectInputHandler());
        this.quantityInput.addChangeListener(new EstimatedValueHandler());
        
    }
    
    public void paint(){
        var selectInputContainer = Popup.createInputContainer("Nome do Produto: ", this.selectInput);
        var qttyInputContainer = Popup.createInputContainer("Quantidade: ", this.quantityInput);
        var estimatedValueContainer = Popup.createInputContainer("Valor estimado: ", this.estimatedValue);
                
        selectInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        qttyInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        this.setBorder(new EmptyBorder(20, 0, 20, 0));
        this.setLayout(new GridBagLayout());
        
        Popup.setComponentInHorizontalGrid(this, selectInputContainer, 0, 0, 2);
        Popup.setComponentInHorizontalGrid(this, qttyInputContainer, 2, 0, 1);
        Popup.setComponentInHorizontalGrid(this, estimatedValueContainer, 3, 0, 1);
        
    }
    
    private String[] createSelectOptions(List<Item> options){
        var castedOptions = new String[options.size() + 1];
        
        castedOptions[0] = "Selecione um item";
        
        for(int i = 1; i < castedOptions.length; i++){
            castedOptions[i] = options.get(i - 1).getName();
        }
        
        return castedOptions;
    }
}
