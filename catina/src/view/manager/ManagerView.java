/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.manager;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import main.Environment;
import view.View;

/**
 *
 * @author Vinicius Jimenez
 */
public final class ManagerView extends JFrame implements View{


    public ManagerView(){
        this.init();
        this.paint();
    }
    
    @Override
    public void init(){ 
        this.setSize(1080, 720); 
        this.setResizable(false);
        this.setTitle("CaTina - Home");
        this.setLocationRelativeTo(null);
        this.setIconImage(Environment.LOGO_ICON.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint() {
        this.setVisible(true);
    }
}
