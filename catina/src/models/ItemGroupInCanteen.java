/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author Dyogo
 */
public class ItemGroupInCanteen {
    String name;
    double price;
    int quantity;

    public ItemGroupInCanteen(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public ItemGroupInCanteen(String name, double price) {
        this.name = name;
        this.price = price;        
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
        if (!(o instanceof ItemGroupInCanteen)) {
            return false;
        }
          
        // typecast o to Complex so that we can compare data members 
        ItemGroupInCanteen c = (ItemGroupInCanteen) o;
          
        // Compare the data members and return accordingly 
        return this.name.equals(c.name) && this.price == c.price;
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
