/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.report;

import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.swing.JRViewer;
import view.ComponentInterface;

/**
 *
 * @author Dyogo
 */
public final class ReportView extends JDialog implements ComponentInterface {
    JRViewer report;
    
    public ReportView(JRViewer report){
        super(new JFrame(), "Report", true);
        this.report = report;
        
        this.init();
        this.paint();
    }
    
    @Override
    public void init() {        
        this.setSize(1080, 720);                            
    }

    @Override
    public void paint() {
        this.getContentPane().add(this.report);  
        this.setVisible(true);
    }
    
}
