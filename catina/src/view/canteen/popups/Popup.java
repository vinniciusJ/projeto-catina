/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import main.Environment;

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

    public class CancelOperationHandler implements ActionListener{
        private final Supplier callback;
        
        CancelOperationHandler(Supplier callback){
            this.callback = callback;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            callback.get();
            closePopup();
        }
    }
        
    public abstract void onSave(Consumer<HashMap<String, Object>> callback);  
    
    public void onCancel(Supplier callback){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                callback.get();
            }
        });
        
        this.closeButton.addActionListener(new CancelOperationHandler(callback));
    };
    
    public void addChildren(Component children){
        this.add(children);
    }
    
    public static void setComponentInHorizontalGrid(JPanel parent, Component component, int x, int y, int width){
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
    
    public static JPanel createInputContainer(String labelText, Component input){
        var panel = new JPanel();
        var label = new JLabel(labelText);
        
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        label.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(input);
        
        return panel;
    }
}
