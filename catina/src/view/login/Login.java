/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import view.AbstractComponent;

/**
 *
 * @author Vinicius Jimenez
 */
public class Login extends AbstractComponent{
    private final JFrame container = new JFrame();
    
    public Login(){
        this.init();
    }
    
    public final void init(){
        this.container.setTitle("CaTina - Login");
        this.container.setSize(new Dimension(760, 520));
        this.container.setResizable(false);
        this.container.setLocationRelativeTo(null);
        this.container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void addSubmitEvent(ActionListener listener){
        
    }
    
    
    public void show(){
        var welcomeLabel = new JLabel("BEM-VINDO!", SwingConstants.CENTER);
        var loginForm = new LoginForm();
        
        welcomeLabel.setFont(new Font("Sans-Serif", Font.BOLD, 28));
        welcomeLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        this.container.add(Box.createVerticalGlue(), BorderLayout.WEST);
       
        
        this.container.add(welcomeLabel, BorderLayout.NORTH);
        this.container.add(loginForm.getContainer(), BorderLayout.CENTER);
        
        this.container.setVisible(true);
    }
}
