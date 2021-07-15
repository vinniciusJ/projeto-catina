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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import view.ClickEventHandler;
import view.ComponentInterface;

/**
 *
 * @author Vinicius Jimenez
 */

public final class LoginForm extends JPanel implements ComponentInterface{
    private final JButton submitButton, registerButton;
    private final JTextField userInput;
    private final JPasswordField passwordInput;
    private final JLabel unmatchedCredentials;
   
    private class LoginHandler extends ClickEventHandler{
        LoginHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {        
            var args = new HashMap<String, Object>(){{
                put("username", userInput.getText());
                put("password", new String(passwordInput.getPassword()));
            }};
            
            this.callback.accept(args);
        }  
    }
    
    private class RegisterHandler extends ClickEventHandler{
        RegisterHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            this.callback.accept(null);
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
    
    private class InputTextHandler implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            unmatchedCredentials.setVisible(false);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            unmatchedCredentials.setVisible(false);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            unmatchedCredentials.setVisible(false);
        }
    }
   
     public LoginForm(){
        this.submitButton = new JButton();
        this.registerButton = new JButton();
        this.userInput = new JTextField();
        this.passwordInput = new JPasswordField();
        this.unmatchedCredentials = new JLabel();
        
        this.init();
        this.paint();
    }

    public void addLoginEventHandler(Consumer<HashMap<String, Object>> onSubmit){
        this.submitButton.addActionListener(new LoginHandler(onSubmit));
    }
    
    public void addRegisterEventHandler(Consumer onCreateUser){
        this.registerButton.addActionListener(new RegisterHandler(onCreateUser));
    }
   
    public void notifyUnmatchedCredentials(){
       this.unmatchedCredentials.setVisible(true);
    }
    
    public void setCredentials(String username, String password){
        this.userInput.setText(username);
        this.passwordInput.setText(password);
    }
    
    @Override
    public void init(){        
        this.submitButton.setText("Entrar");
        this.submitButton.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        
        this.registerButton.setText("Criar nova conta");
        this.registerButton.setBorderPainted(false); 
        this.registerButton.setContentAreaFilled(false); 
        this.registerButton.setFocusPainted(false); 
        this.registerButton.setOpaque(false);
        this.registerButton.setBorder(null);
        this.registerButton.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        
        this.registerButton.addMouseListener(new MouseEffectsHandler());
        
        this.unmatchedCredentials.setText("Usuário e/ou senha estão incorretos!");
        this.unmatchedCredentials.setForeground(Color.red);
        this.unmatchedCredentials.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.unmatchedCredentials.setVisible(false);

        this.userInput.setColumns(20);
        this.userInput.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        this.userInput.addKeyListener(new InputTextHandler());
      
        this.passwordInput.setColumns(20);
        this.passwordInput.addKeyListener(new InputTextHandler());

        this.setLayout(null);
    }
    
    @Override
    public void paint(){
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
    
  
}
