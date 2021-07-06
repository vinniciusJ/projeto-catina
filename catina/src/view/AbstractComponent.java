/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.HashMap;
import javax.swing.JComponent;

/**
 *
 * @author Vinicius Jimenez
 */
public abstract class AbstractComponent {
    // Bom, cada elemento irá ter uma `key` para que possamos pegar ele no `controller`
    private HashMap<String, JComponent> children = new HashMap<>();
    
    public void addComponent(String key, JComponent component){
        this.children.put(key, component);
    }
    
    public void removeComponent(String key){
        this.children.remove(key);
    }

    /**
     * @return the children
     */
    public HashMap<String, JComponent> getChildren() {
        return this.children;
    }
}
