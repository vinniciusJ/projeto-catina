/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import controllers.AdministratorController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vinicius Jimenez
 */
public class Login{
    private final JFrame container;
    private final LoginForm loginForm;
    private final AdministratorController admController;

    public Login(){
        this.container = new JFrame();
        this.loginForm = new LoginForm();
        this.admController =  new AdministratorController();
        this.init();
    }
    
    public final void init(){
        this.container.setTitle("CaTina - Login");
        this.container.setSize(new Dimension(760, 520));
        this.container.setResizable(false);
        this.container.setLocationRelativeTo(null);
        this.container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addSubmitEvent((username, password) -> {
            try {
                this.admController.login(username, password);
                System.out.println("Deu tudo certo!");
            } catch (Exception ex) {
                System.out.println("Deu tudo errado System.exit(0)");
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void addSubmitEvent(BiConsumer<String, String> callback){
        this.loginForm.addSubmitEventHandler(callback);
    }
    
    
    public void show(){
        var welcomeLabel = new JLabel("BEM-VINDO!", SwingConstants.CENTER);
  
        welcomeLabel.setFont(new Font("Sans-Serif", Font.BOLD, 28));
        welcomeLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        this.container.add(welcomeLabel, BorderLayout.NORTH);
        this.container.add(this.loginForm.getContainer(), BorderLayout.CENTER);
        
        this.container.setVisible(true);
    }
}
