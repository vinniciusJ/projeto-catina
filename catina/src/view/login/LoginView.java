/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.border.EmptyBorder;
import main.Environment;

/**
 *
 * @author Vinicius Jimenez
 */
public class LoginView extends JFrame{
    private final LoginForm loginForm;

    public LoginView(){
        this.loginForm = new LoginForm();

        this.init();
        this.paint();
    }
    
    public final void init(){
        this.setIconImage(Environment.LOGO_ICON.getImage());
        this.setTitle("CaTina - Login");
        this.setSize(new Dimension(760, 520));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public void addSubmitEvent(BiConsumer<String, String> callback){
        this.loginForm.addSubmitEventHandler(callback);
    }
    
    public void addRegisterEvent(Consumer callback){
        this.loginForm.addRegisterEventHandler(callback);
    }
    
    public void notifyUnmatchedCredentials(){
        this.loginForm.notifyUnmatchedCredentials();
    }
    
    public void closeLoginWindow(){
        this.setVisible(false);
        this.dispose();
    }
    
    public final void paint(){
        var header = new JPanel();
        var welcomeLabel = new JLabel("BEM-VINDO!", SwingConstants.CENTER);
        var copyrightLabel = new JLabel("<html>&copy; CaTina<html>", SwingConstants.CENTER);
        var userIcon = new JLabel(new ImageIcon("src/images/user.png"), SwingConstants.CENTER);
                
        header.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        welcomeLabel.setFont(new Font("Sans-Serif", Font.BOLD, 28));
        welcomeLabel.setBorder(new EmptyBorder(15, 10, 15, 0));
        
        copyrightLabel.setFont(new Font("Sans-Serif", Font.ITALIC, 18));
        copyrightLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        header.add(userIcon);
        header.add(welcomeLabel);
        
        this.add(header, BorderLayout.NORTH);
        this.add(this.loginForm, BorderLayout.CENTER);
        this.add(copyrightLabel, BorderLayout.SOUTH);
      
        
        this.setVisible(true);
    }
    
    public void setCredentials(String username, String password){
        this.loginForm.setCredentials(username, password);
    }
}
