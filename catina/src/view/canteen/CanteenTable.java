/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CanteenItem;
import view.View;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenTable extends JTable{
    private final String[] headers = {"Nome: ", "Tipo: ", "Quantidade: ", "Preço: "};
    private List<CanteenItem> rows = new ArrayList<>();
    private final DefaultTableModel model;
    
    public CanteenTable(List<CanteenItem> rows){
        this.rows = rows;
        
        
        
        this.model = new DefaultTableModel(this.setRowsToObjectMatrix(), this.headers);
        this.setModel(this.model);
    }
    
    private Object[][] setRowsToObjectMatrix(){
        var convertedRows = new Object[this.rows.size()][4];
        
        for(int i = 0; i < this.rows.size(); i++){
            var item = this.rows.get(i);
            
            var name = item.getItem().getName();
            var type = item.getItem().getType();
            var price = item.getItem().getPrice();
            var qtty = item.getQuantity();
            
            Object[] row = {name, type, price, qtty};
                       
            convertedRows[i] = row;
        }
        
        return convertedRows;
    }

}
