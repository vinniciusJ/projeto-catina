/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 *
 * @author Vinicius Jimenez
 */
public abstract class ClickEventHandler implements ActionListener{
    public Consumer<HashMap<String, Object>> callback;
    
    public ClickEventHandler(){}
    
    public ClickEventHandler(Consumer<HashMap<String, Object>> callback){
        this.callback = callback;
    }
}