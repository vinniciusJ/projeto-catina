/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.CanteenItem;
import view.View;


/**
 *
 * @author Vinicius Jimenez
 */
public class CanteenItemList extends JPanel implements View{
   private ArrayList<CanteenItem> items;
   
    private final class ListItem extends JPanel implements View{
        private final CanteenItem data;
        
        public ListItem(CanteenItem item){
            this.data = item;
            
            this.init();
            this.paint();
        }

        @Override
        public void init() {
            this.setBorder(new EmptyBorder(0, 0, 20, 0));
            this.setLayout(new GridBagLayout());
        }

        @Override
        public void paint() {
            var itemName = new JLabel(this.data.getItem().getName());
            var itemPrice = new JLabel("R$ " + Float.toString(this.data.getItem().getPrice()));
            var itemQtty = new JLabel(this.data.getQuantity() + " unidades");
            
            var itemNameGc = new GridBagConstraints();
            var itemPriceGc = new GridBagConstraints();
            var itemQttyGc = new GridBagConstraints();
            
            itemNameGc.fill = GridBagConstraints.HORIZONTAL;
            itemNameGc.gridx = 0;
            itemNameGc.gridy = 0;
            itemNameGc.gridwidth = 2;
            
            itemPriceGc.fill = GridBagConstraints.HORIZONTAL;
            itemPriceGc.gridx = 2;
            itemPriceGc.gridy = 0;
            
            itemQttyGc.fill = GridBagConstraints.HORIZONTAL;
            itemQttyGc.gridx = 3;
            itemQttyGc.gridy = 0;
            
            
            this.add(itemName, itemNameGc);
            this.add(itemPrice, itemPriceGc);
            this.add(itemQtty, itemQttyGc);
        }
    }
    
    public CanteenItemList(){}
    
    public CanteenItemList(ArrayList<CanteenItem> items){
        this.items = items;
    }
    
    
    @Override
    public void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0, 40, 0, 40));
    }

    @Override
    public void paint() {
        this.items.forEach(item -> this.add(new ListItem(item)));
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<CanteenItem> items) {
        this.items = items;
    }

    /**
     * @return the items
     */
    public ArrayList<CanteenItem> getItems() {
        return items;
    }
}
