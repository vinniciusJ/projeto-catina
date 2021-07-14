/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import main.Environment;
import view.EventHandler;
import view.View;
import view.canteen.popups.Popup;

/**
 *
 * @author Vinicius Jimenez
 */
public final class RegisterView extends JFrame implements View{
    private final JTextField userNameInput, canteenNameInput;
    private final JPasswordField userPasswordInput;
    private final JButton saveButton, cancelButton;
    
    public RegisterView(){
        this.userNameInput = new JTextField();
        this.canteenNameInput = new JTextField();
        this.userPasswordInput = new JPasswordField();
        this.saveButton = new JButton();
        this.cancelButton = new JButton();
        
        this.init();
        this.paint();
    }
    
    private class CancelOperationHandler extends EventHandler{
        CancelOperationHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            this.callback.accept(null);
        } 
    }
    
    private class RegisterUserHandler extends EventHandler{
        RegisterUserHandler(Consumer callback){
            super(callback);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            var username = (String) userNameInput.getText();
            var canteenName = (String) canteenNameInput.getText();
            var password = new String(userPasswordInput.getPassword());
            
            if(!isAllFieldsFilled(new String[]{ username, canteenName, password })){
                showFillingAllFieldsMessage();
            }
            else{
                var args = new HashMap<String, Object>(){{
                    put("username", username);
                    put("canteenName", canteenName);
                    put("password", password);
                }};
                
                this.callback.accept(args);
            }
        }
        
        public boolean isAllFieldsFilled(String[] values){
            var status = true;
            
            for(String value: values){
                status = !(value.isEmpty() || value.isBlank());
            }
            
            return status;
        }
    }
    
    @Override
    public void init() {
        this.setSize(720, 480); 
        this.setResizable(false);
        this.setTitle("Criar usuário");
        this.setLocationRelativeTo(null);
        this.setIconImage(Environment.LOGO_ICON.getImage());
        
        var defaultButtonFont = new Font("Sans-Serif", Font.PLAIN, 18);
        var defaultButtonPadding = BorderFactory.createCompoundBorder(this.saveButton.getBorder(), new EmptyBorder(5, 10, 5, 10));
        var defaultInputPadding = BorderFactory.createCompoundBorder(this.userNameInput.getBorder(), new EmptyBorder(3, 10, 3, 10));
        
        this.userNameInput.setBorder(defaultInputPadding);
        this.userPasswordInput.setBorder(defaultInputPadding);
        this.canteenNameInput.setBorder(defaultInputPadding);
        
        this.saveButton.setText("Cadastrar");
        this.saveButton.setFont(defaultButtonFont);
        this.saveButton.setBorder(defaultButtonPadding);
        
        this.cancelButton.setText("Cancelar");
        this.cancelButton.setFont(defaultButtonFont);
        this.cancelButton.setBorder(defaultButtonPadding);
    }

    @Override
    public void paint(){
        var mainContainer = new JPanel();
        var optionsContainer = new JPanel();
        var title = new JLabel("Registre-se", SwingConstants.CENTER);
                
        var userNameContainer = this.createInputContainer("Nome do usuário: ", this.userNameInput);
        var canteenNameContainer = this.createInputContainer("Nome da cantina: ", this.canteenNameInput);
        var userPasswordContainer = this.createInputContainer("Senha do usuário: ", this.userPasswordInput); 
        
        var defaultPadding = new EmptyBorder(0, 0, 20, 0);
        
        title.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        optionsContainer.setBorder(new EmptyBorder(20, 0, 40, 0));
        
        canteenNameContainer.setBounds(80, 0, 560, 80);
        userNameContainer.setBounds(80, 80, 560, 80); 
        userPasswordContainer.setBounds(80, 160, 560, 80);
        
        canteenNameContainer.setBorder(defaultPadding);
        userNameContainer.setBorder(defaultPadding);
        userPasswordContainer.setBorder(defaultPadding);
        
        mainContainer.setLayout(null);
        
        optionsContainer.add(this.saveButton);
        optionsContainer.add(Box.createGlue());
        optionsContainer.add(this.cancelButton);
       
        mainContainer.add(canteenNameContainer);
        mainContainer.add(userNameContainer);
        mainContainer.add(userPasswordContainer);
        
        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
        this.add(optionsContainer, BorderLayout.SOUTH);
        
        
        this.setVisible(true);
    }
    
    public void close(){
        this.setVisible(false);
        this.dispose();
    }
    
    public void onSave(Consumer<HashMap<String, Object>> callback){
        this.saveButton.addActionListener(new RegisterUserHandler(callback));
    }
    
    public void onCancel(Consumer<HashMap<String, Object>> callback){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                callback.accept(null);
            }
        });
        
        this.cancelButton.addActionListener(new CancelOperationHandler(callback));
    }
    
    private void showFillingAllFieldsMessage(){
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos para registrar-se", "Registre-se", JOptionPane.WARNING_MESSAGE);
    }
    
    private JPanel createInputContainer(String labelText, Component input){
        var panel = new JPanel();
        var label = new JLabel(labelText);
        
        label.setBorder(new EmptyBorder(0, 0, 20, 0));
        label.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
           
        
        panel.setLayout(new GridLayout(2, 1));
        
        panel.add(label);
        panel.add(input);
        
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        return panel;
    }
}
