/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.CanteenItem;
import view.View;


/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenItemList extends JPanel implements View{
    private ArrayList<CanteenItem> items;
    
    final class ListItem extends JPanel implements View{
        private String name, price, qtty;
        
        public ListItem(CanteenItem product){
            /*
            this.name = product.getItem().getName();
            this.price = "R$ " + Float.toString(product.getItem().getPrice()); 
            this.qtty = Integer.toString(product.getQuantity) + " unidade(s)";
            */
            this.name = "Bolo de Cenoura";
            this.price = "R$ 10,00";
            this.qtty = "10 unidades";
            
            this.init();
            this.paint();
        }

        @Override
        public void init() {
            this.setSize(200, 50);
           this.setBackground(Color.white);
        }

        @Override
        public void paint() {
            var itemName = new JLabel(this.name);
            var itemPrice = new JLabel(this.price);
            var itemQtty = new JLabel(this.qtty);
            
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
    
    public CanteenItemList(ArrayList<CanteenItem> items){
        this.items = items;
        
        this.init();
        this.paint();
    }
       
    @Override
    public void init() {
        var layout = new GridLayout(this.items.size() + 1, 1, 0, 15);

        this.setLayout(layout);
        this.setBorder(new EmptyBorder(0, 80, 0, 80));
    }

    @Override
    public void paint() {
        var listIterator = this.items.iterator();
        
        while(listIterator.hasNext()){
            this.add(new ListItem(listIterator.next()));
        }
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<CanteenItem> items) {
        this.items = items;
    }

}
