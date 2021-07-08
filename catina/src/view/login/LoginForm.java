/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.BiConsumer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vinicius Jimenez
 */

public class LoginForm implements ActionListener, KeyListener{
   private final JPanel container;
   private final JButton submitButton;
   private final JTextField userInput;
   private final JPasswordField passwordInput;
   private final JLabel unmatchedCredentials;
   
   private BiConsumer<String, String> onSubmit;
   
   public LoginForm(){
        this.container = new JPanel();
        this.submitButton = new JButton();
        this.userInput = new JTextField();
        this.passwordInput = new JPasswordField();
        this.unmatchedCredentials = new JLabel();
        
        this.onSubmit = (user, password) -> {};
        
        this.init();
        this.show();
   }

    public final void init(){        
        this.submitButton.setText("Entrar");
        this.submitButton.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        this.submitButton.addActionListener(this);

        this.unmatchedCredentials.setText("Usuário e/ou senha estão incorretos!");
        this.unmatchedCredentials.setForeground(Color.red);
        this.unmatchedCredentials.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.unmatchedCredentials.setVisible(false);

        this.userInput.setColumns(20);
        this.userInput.addKeyListener(this);
      
        this.passwordInput.setColumns(20);
        this.passwordInput.addKeyListener(this);

        this.container.setLayout(null);
    }
   
    public void addSubmitEventHandler(BiConsumer<String, String> onSubmit){
        this.onSubmit = onSubmit;
    }
   
    public void notifyUnmatchedCredentials(){
        this.unmatchedCredentials.setVisible(true);
    }

    public final void show(){
        var userInputContainer = new JPanel();
        var passwordInputContainer = new JPanel();
        
        var userInputLabel = new JLabel("Usuário:", SwingConstants.CENTER);
        var passwordInputLabel = new JLabel("Senha:", SwingConstants.LEFT);
        
        userInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        userInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        userInputContainer.setBounds(100, 40, 560, 60);
        userInputContainer.setLayout(new BoxLayout(userInputContainer, BoxLayout.Y_AXIS));
       
        passwordInputLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        passwordInputLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
       
        passwordInputContainer.setBounds(100, 120, 560, 60);
        passwordInputContainer.setLayout(new BoxLayout(passwordInputContainer, BoxLayout.Y_AXIS));

        this.unmatchedCredentials.setBounds(240, 200, 280, 40);
        this.submitButton.setBounds(320, 250, 120, 60);
        
        // Adicionando os elementos aos seus containers
        
        userInputContainer.add(userInputLabel);
        passwordInputContainer.add(passwordInputLabel);
        
        userInputContainer.add(this.userInput);
        passwordInputContainer.add(this.passwordInput);
        
        this.container.add(userInputContainer);
        this.container.add(passwordInputContainer); 
        this.container.add(this.unmatchedCredentials);
        this.container.add(this.submitButton);
   }

    /**
     * @return the container
     */
    public JPanel getContainer() {
        return container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var user = this.userInput.getText();
        var password = new String(this.passwordInput.getPassword());
        
        this.onSubmit.accept(user, password);
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
