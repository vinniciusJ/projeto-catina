/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CanteenItem;
import view.View;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenTable extends JTable implements View{
    private final String[] headers = {"Nome: ", "Tipo: ", "Preço: ", "Quantidade: "};
    private final Object[][] rows;
    private final DefaultTableModel model;
   
    
    public CanteenTable(List<CanteenItem> rows){
        this.rows = CanteenTable.setRowsToObjectMatrix(rows);
        
        this.model = new DefaultTableModel(this.rows, this.headers);
        this.setModel(this.model);
        
        this.init();
    }
    
    private static Object[][] setRowsToObjectMatrix(List<CanteenItem> rows){
        var convertedRows = new Object[rows.size()][4];
        
        for(int i = 0; i < rows.size(); i++){
            convertedRows[i] = CanteenTable.getCanteenItemsValues(rows.get(i));
        }
        
        return convertedRows;
    }
    
    private static Object[] getCanteenItemsValues(CanteenItem item){
        var name = item.getItem().getName();
        var type = item.getItem().getType();
        var price = item.getItem().getPrice();
        var qtty = item.getQuantity();
        
        return new Object[]{name, type, price, qtty};
    }
    
    @Override
    public void init() {
        var header = this.getTableHeader();
        
        header.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        
        this.setRowHeight(40);
        this.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.setIntercellSpacing(new Dimension(15, 15));
    }

    @Override
    public void paint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
