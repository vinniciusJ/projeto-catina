/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Vinicius Jimenez
 */

public class LoginForm extends JPanel implements KeyListener{
    private final JButton submitButton, registerButton;
    private final JTextField userInput;
    private final JPasswordField passwordInput;
    private final JLabel unmatchedCredentials;
   
    private BiConsumer<String, String> onSubmit;
    private Consumer onRegister;
   
    private class LoginHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            var user = userInput.getText();
            var password = new String(passwordInput.getPassword());
        
            onSubmit.accept(user, password);
        }  
    }
    
    private class RegisterHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            onRegister.accept(null);
        }  
    }
    
    private class MouseEffectsHandler extends MouseAdapter{
        private Font original;
       
        @Override
        public void mouseEntered(MouseEvent evt) {
            this.original = evt.getComponent().getFont();
            
            var attributes = (Map) original. getAttributes();
            
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            
            evt.getComponent().setFont(original.deriveFont(attributes));
        }

        @Override
        public void mouseExited(MouseEvent evt){
            evt.getComponent().setFont(original);   
        }

        @Override
        public void mouseClicked(MouseEvent evt) {
            evt.getComponent().setFont(original); 
        }
    }
   
     public LoginForm(){
        this.submitButton = new JButton();
        this.registerButton = new JButton();
        this.userInput = new JTextField();
        this.passwordInput = new JPasswordField();
        this.unmatchedCredentials = new JLabel();
        
        this.onSubmit = (user, password) -> {};
        this.onRegister = data -> {};
        
        this.init();
        this.paint();
   }

    public final void init(){        
        this.submitButton.setText("Entrar");
        this.submitButton.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        this.submitButton.addActionListener(new LoginHandler());
        
        this.registerButton.setText("Criar nova conta");
        this.registerButton.setBorderPainted(false); 
        this.registerButton.setContentAreaFilled(false); 
        this.registerButton.setFocusPainted(false); 
        this.registerButton.setOpaque(false);
        this.registerButton.setBorder(null);
        this.registerButton.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        this.registerButton.addMouseListener(new MouseEffectsHandler());
        this.registerButton.addActionListener(new RegisterHandler());

        this.unmatchedCredentials.setText("Usuário e/ou senha estão incorretos!");
        this.unmatchedCredentials.setForeground(Color.red);
        this.unmatchedCredentials.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.unmatchedCredentials.setVisible(false);

        this.userInput.setColumns(20);
        this.userInput.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        this.userInput.addKeyListener(this);
      
        this.passwordInput.setColumns(20);
        this.passwordInput.addKeyListener(this);

        this.setLayout(null);
    }
   
    public void addSubmitEventHandler(BiConsumer<String, String> onSubmit){
        this.onSubmit = onSubmit;
    }
    
    public void addRegisterEventHandler(Consumer onCreateUser){
        this.onRegister = onCreateUser;
    }
   
    public void notifyUnmatchedCredentials(){
       this.unmatchedCredentials.setVisible(true);
    }

    public final void paint(){
        var userInputContainer = new JPanel();
        var passwordInputContainer = new JPanel();
        
        var userInputLabel = new JLabel("Usuário:", SwingConstants.LEFT);
        var passwordInputLabel = new JLabel("Senha:", SwingConstants.LEFT);
        
        userInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        userInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        userInputContainer.setBounds(100, 20, 560, 60);
        userInputContainer.setLayout(new GridLayout(2, 1));
       
        passwordInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        passwordInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
       
        passwordInputContainer.setBounds(100, 100, 560, 60);
        passwordInputContainer.setLayout(new GridLayout(2, 1));

        this.unmatchedCredentials.setBounds(240, 175, 280, 40);
        this.submitButton.setBounds(320, 225, 120, 45);
        this.registerButton.setBounds(320, 285, 120, 20);
        
        // Adicionando os elementos aos seus containers
        
        userInputContainer.add(userInputLabel);
        passwordInputContainer.add(passwordInputLabel);
        
        userInputContainer.add(this.userInput);
        passwordInputContainer.add(this.passwordInput);
        
        this.add(userInputContainer);
        this.add(passwordInputContainer); 
        
        this.add(this.unmatchedCredentials);
        this.add(this.submitButton);
        this.add(this.registerButton);
   }
    
    public void setCredentials(String username, String password){
        this.userInput.setText(username);
        this.passwordInput.setText(password);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        this.unmatchedCredentials.setVisible(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.unmatchedCredentials.setVisible(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.unmatchedCredentials.setVisible(false);
    }
    
}
