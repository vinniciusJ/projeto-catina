/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Item;
import view.View;


/**
 *
 * @author Vinicius Jimenez
 */
public class CanteenItemList extends JPanel implements View{
   private ArrayList<Item> items;
   
    private class ListItem extends JPanel{
        
        public ListItem(Item item){
            
        }
    }
    
    public CanteenItemList(){}
    
    public CanteenItemList(ArrayList<Item> items){
        this.items = items;
    }
    
    
    @Override
    public void init() {
        this.setBackground(Color.red);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0, 40, 0, 40));
    }

    @Override
    public void paint() {
        
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
