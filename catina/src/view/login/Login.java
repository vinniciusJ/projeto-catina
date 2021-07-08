/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controllers.ManagerController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.*;
import java.util.function.BiConsumer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vinicius Jimenez
 */
public class Login{
    private final JFrame container;
    private final LoginForm loginForm;
    private final ManagerController admController;

    public Login(){
        this.container = new JFrame();
        this.loginForm = new LoginForm();

        this.admController =  new ManagerController();

        this.init();
    }
    
    public final void init(){
        var icon = new ImageIcon("src/images/logo.png");
        
        this.container.setIconImage(icon.getImage());
        this.container.setTitle("CaTina - Login");
        this.container.setSize(new Dimension(760, 520));
        this.container.setResizable(false);
        this.container.setLocationRelativeTo(null);
        this.container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addSubmitEvent((username, password) -> {
            try {
                this.admController.login(username, password);
                this.container.setVisible(false);
                this.container.dispose();
                
            } catch (Exception ex) {
                this.loginForm.notifyUnmatchedCredentials();
            }
        });
    }
    
    public void addSubmitEvent(BiConsumer<String, String> callback){
        this.loginForm.addSubmitEventHandler(callback);
    }
    
    public void show(){
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
        
        this.container.add(header, BorderLayout.NORTH);
        this.container.add(this.loginForm.getContainer(), BorderLayout.CENTER);
        this.container.add(copyrightLabel, BorderLayout.SOUTH);
      
        
        this.container.setVisible(true);
    }
}
