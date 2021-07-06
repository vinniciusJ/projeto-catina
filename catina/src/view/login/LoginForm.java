/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import view.AbstractComponent;

/**
 *
 * @author Vinicius Jimenez
 */
public class LoginForm extends AbstractComponent{
   private final JPanel container = new JPanel();

   public LoginForm(){
       this.init();
       this.show();
   }

   public final void init(){
       this.container.setLayout(null);
   }

   public final void show(){
        var userInputContainer = new JPanel();
        var userInputLabel = new JLabel("Usuário:", SwingConstants.CENTER);
        var userInput = new JTextField(25);
        
        var passwordInputContainer = new JPanel();
        var passwordInputLabel = new JLabel("Senha:", SwingConstants.LEFT);
        var passwordInput = new JTextField(25);
        
        var submitButton = new JButton("Entrar");

        userInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        userInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        userInputContainer.setBounds(100, 40, 560, 60);
        userInputContainer.setLayout(new BoxLayout(userInputContainer, BoxLayout.Y_AXIS));
       
        passwordInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        passwordInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
       
        passwordInputContainer.setBounds(100, 120, 560, 60);
        passwordInputContainer.setLayout(new BoxLayout(passwordInputContainer, BoxLayout.Y_AXIS));

        submitButton.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        submitButton.setBounds(320, 220, 120, 60);
        
        // Adicionando os elementos aos seus containers
        
        userInputContainer.add(userInputLabel);
        userInputContainer.add(userInput);
        passwordInputContainer.add(passwordInputLabel);
        passwordInputContainer.add(passwordInput);

        this.addComponent("submit-button", submitButton);

        this.container.add(userInputContainer);
        this.container.add(passwordInputContainer); 
        this.container.add(submitButton);
   }

    /**
     * @return the container
     */
    public JPanel getContainer() {
        return container;
    }
    
}
