/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;
import javax.swing.BoxLayout;
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

public class LoginForm implements ActionListener{
   private final JPanel container;
   private final JButton submitButton;
   private final JTextField userInput;
   private final JPasswordField passwordInput;
   private BiConsumer<String, String> onSubmit;
   
   public LoginForm(){
        this.container = new JPanel();
        this.submitButton = new JButton();
        this.userInput = new JTextField();
        this.passwordInput = new JPasswordField();
        this.onSubmit = (user, password) -> {};
        
        this.init();
        this.show();
   }

   public final void init(){
       this.submitButton.setText("Entrar");
       this.submitButton.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
       this.submitButton.addActionListener(this);
       
       this.userInput.setColumns(25);
       this.passwordInput.setColumns(25);
       
       this.container.setLayout(null);
   }
   
   public void addSubmitEventHandler(BiConsumer<String, String> onSubmit){
       this.onSubmit = onSubmit;
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

        this.submitButton.setBounds(320, 220, 120, 60);
        
        // Adicionando os elementos aos seus containers
        
        userInputContainer.add(userInputLabel);
        passwordInputContainer.add(passwordInputLabel);
        
        userInputContainer.add(this.userInput);
        passwordInputContainer.add(this.passwordInput);
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        var user = this.userInput.getText();
        var password = new String(this.passwordInput.getPassword());
        
        this.onSubmit.accept(user, password);
    }
    
}
