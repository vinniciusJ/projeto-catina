/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Dyogo
 */
public class ItemGroup {
    String name;
    double price;
    int quantity;
    String type;
    ArrayList <Item> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemGroup(String name, String type, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }
    
    public ItemGroup(String name, double price, String type, Item item) {
        this.name = name;
        this.price = price;        
        this.type = type;
        this.items = new ArrayList<>();
        this.items.add(item);
    }
    
    public void addItem(Item item){        
        this.items.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void increaseQuantity(){
        this.quantity ++;
    }
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
  
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
  
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ItemGroup)) {
            return false;
        }
                  
        ItemGroup c = (ItemGroup) o;
          
        // Compare the data members and return accordingly 
        boolean comparison = this.name.toLowerCase().equals(c.name.toLowerCase()) && this.price == c.price;
        System.out.println(comparison);
        return comparison;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 37 * hash + this.quantity;
        return hash;
    }

           
}
