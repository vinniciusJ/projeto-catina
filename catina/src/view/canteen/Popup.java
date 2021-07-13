/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import main.Environment;
import models.ModelDatabase;

/**
 *
 * @author Vinicius Jimenez
 */
public abstract class Popup extends JFrame{
    public final JButton saveButton, closeButton;
    
    public Popup(String title, Dimension dimension){           
        this.setTitle(title);
        this.setSize(dimension);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(Environment.LOGO_ICON.getImage());
        
        this.saveButton = new JButton("Salvar");
        this.closeButton = new JButton("Cancelar");
    }
    
        
    public abstract void onSave(Consumer<HashMap<String, Object>> callback);  
    public abstract void onCancel(Supplier callback);
    
    public void addChildren(Component children){
        this.add(children);
    }
    
    public void setComponentInHorizontalGrid(JPanel parent, Component component, int x, int y, int width){
        var constraints = new GridBagConstraints();
        
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        
        parent.add(component, constraints);
    }

    public void showPopup(){
        this.setVisible(true);
    }

    public void closePopup(){
        this.setVisible(false);
        this.dispose();
    }
    
    public JPanel createInputContainer(String labelText, Component input){
        var panel = new JPanel();
        var label = new JLabel(labelText);
        
        label.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        label.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(input);
        
        return panel;
    }
}
