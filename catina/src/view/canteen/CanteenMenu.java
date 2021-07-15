/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.ComponentInterface;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenMenu extends JPanel implements ComponentInterface{
    private final ButtonMenu registerSale, viewProfit, registerItem, editItem;
    
    private class ButtonMenu extends JButton{
        public ButtonMenu(){
            this.setFont(new Font("Sans-Serif", Font.BOLD, 16));
            this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), new EmptyBorder(10, 6, 10, 6)));
            this.setIconTextGap(10);
        }
    }
    
    public CanteenMenu(){
        this.registerSale = new ButtonMenu();
        this.viewProfit = new ButtonMenu();
        this.registerItem = new ButtonMenu();
        this.editItem = new ButtonMenu();
        
        this.init();
        this.paint();
    }
    
    public void addRegisterSaleEventHandler(ActionListener handler){
        this.registerSale.addActionListener(handler);
    }
    
    public void addViewProfitEventHandler(ActionListener handler){
        this.viewProfit.addActionListener(handler);
    }
    
    public void addRegisterItemEventHandler(ActionListener handler){
        this.registerItem.addActionListener(handler);
    }
    
    public void addEditItemEventHandler(ActionListener handler){
        this.editItem.addActionListener(handler);
    }

    @Override
    public void init() {    
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 0));
        this.setBorder(new EmptyBorder(20, 0, 40, 0));

        this.registerSale.setText("Registrar venda");
        this.viewProfit.setText("Visualizar lucro");
        this.registerItem.setText("Cadastrar item");
        this.editItem.setText("Editar item");
        
        this.registerSale.setIcon(new ImageIcon("src/images/selling.png"));
        this.registerItem.setIcon(new ImageIcon("src/images/item.png"));
        this.viewProfit.setIcon(new ImageIcon("src/images/profit.png"));
        this.editItem.setIcon(new ImageIcon("src/images/edit.png"));
    }

    @Override
    public void paint() {
        this.add(this.registerSale);
        this.add(this.viewProfit);
        this.add(this.registerItem);
        this.add(this.editItem);
    }
    

}
