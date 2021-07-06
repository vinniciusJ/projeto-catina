/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import view.AbstractComponent;

/**
 *
 * @author Vinicius Jimenez
 */
public class Login extends AbstractComponent{
    private final JFrame container = new JFrame();
    private final Container contentPane = this.container.getContentPane();
    
    public Login(){
        this.init();
    }
    
    public final void init(){
        this.container.setTitle("CaTina - Login");
        this.container.setPreferredSize(new Dimension(760, 520));
        this.container.setResizable(false);
        this.container.setLocationRelativeTo(null);
        this.container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void addSubmitEvent(ActionListener listener){
        
    }
    
    
    public void show(){

      
                
        var welcomeLabel = new JLabel("WELCOME");
        
        welcomeLabel.setFont(new Font("Sans-Serif", Font.BOLD, 28));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 

  
        
        this.container.add(welcomeLabel, BorderLayout.NORTH);
        this.container.pack();
        
        this.container.setVisible(true);
    }
}
