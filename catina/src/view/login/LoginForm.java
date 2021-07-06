/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import view.AbstractComponent;

/**
 *
 * @author Vinicius Jimenez
 */
public class LoginForm extends AbstractComponent{
   private final JPanel container = new JPanel();

   public LoginForm(){
       this.init();
   }

   public final void init(){
       this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
   }

   public void show(){

   }
    
}
