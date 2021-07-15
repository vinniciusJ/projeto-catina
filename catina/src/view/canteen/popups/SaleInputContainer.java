/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private final JButton deleteInputButton;
    
    private Supplier onDelete;

    private Item selectedItem;
    private int selectedQtty;
    
    public SaleInputContainer(List<Item> items){
        this.options = items;
        
        this.selectedItem = null;
        this.selectedQtty = 0;
        
        this.onDelete = () -> null;
       
        this.deleteInputButton = new JButton();
        this.estimatedValue = new JLabel("R$ 00,00");
        this.selectInput = new JComboBox<>(this.createSelectOptions(items));
        this.quantityInput = new JSpinner(new SpinnerNumberModel(0, 0, 0, 1));
                
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
            if(getSelectedItem() != null){
                var decimalFormat = new DecimalFormat("#.00");
                var qtty = ((Double) quantityInput.getValue()).intValue();
                
                var result = (double) getSelectedItem().getPrice() * qtty;
                
                estimatedValue.setText("R$ " + decimalFormat.format(result));
                selectedQtty = qtty;
            }
        }  
    }
    
    private class DeleteInputHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            onDelete.get();
        } 
    }
   
    
    public void init(){
        this.quantityInput.setEnabled(false);
        
        this.setBorder(new EmptyBorder(0, 0, 20, 0));
        this.setLayout(new GridBagLayout());
        
        this.deleteInputButton.setIcon(new ImageIcon("src/images/delete.png"));
        
        this.estimatedValue.setText("R$ 00,00");
        this.estimatedValue.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        
        this.selectInput.addActionListener(new SelectInputHandler());
        this.quantityInput.addChangeListener(new EstimatedValueHandler());
        this.deleteInputButton.addActionListener(new DeleteInputHandler());
        
    }
    
    public void paint(){
        var selectInputContainer = Popup.createInputContainer("Nome do Produto: ", this.selectInput);
        var qttyInputContainer = Popup.createInputContainer("Quantidade: ", this.quantityInput);
        var estimatedValueContainer = Popup.createInputContainer("Valor estimado: ", this.estimatedValue);
        var deleteButtonContainer = Popup.createInputContainer("", this.deleteInputButton);
                
        selectInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        qttyInputContainer.setBorder(new EmptyBorder(0, 0, 0, 20));
        estimatedValueContainer.setBorder(new EmptyBorder(0, 0, 0, 5));
        
        Popup.setComponentInHorizontalGrid(this, selectInputContainer, 0, 0, 2);
        Popup.setComponentInHorizontalGrid(this, qttyInputContainer, 2, 0, 1);
        Popup.setComponentInHorizontalGrid(this, estimatedValueContainer, 3, 0, 1);
        Popup.setComponentInHorizontalGrid(this, deleteButtonContainer, 4, 0, 1);
    }
    
    public void disableDeleteButton(){
        this.deleteInputButton.setEnabled(false);
    }
    
    private String[] createSelectOptions(List<Item> options){
        var castedOptions = new String[options.size() + 1];
        
        castedOptions[0] = "Selecione um item";
        
        for(int i = 1; i < castedOptions.length; i++){
            castedOptions[i] = options.get(i - 1).getName();
        }
        
        return castedOptions;
    }

    /**
     * @param onDelete the onDelete to set
     */
    public void setOnDelete(Supplier onDelete) {
        this.onDelete = onDelete;
    }

    /**
     * @return the selectedItem
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * @return the selectedQtty
     */
    public int getSelectedQtty() {
        return selectedQtty;
    }

}
