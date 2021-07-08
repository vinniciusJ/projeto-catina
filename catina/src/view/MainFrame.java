/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 *
 * @author Vinicius Jimenez
 */
public class MainFrame extends JFrame{
    private final JDesktopPane desktop;
    
    public MainFrame(){
        this.desktop = new JDesktopPane();
        
        this.init();
        this.paint();
    }
    
    public final void init(){
        this.setSize(1080, 920); 
    }

    private void paint() {
        this.setVisible(true);
    }
}
