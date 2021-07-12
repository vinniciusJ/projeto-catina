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
import javax.swing.table.JTableHeader;
import models.CanteenItem;

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
        
        this.model = new DefaultTableModel(convertedRows, this.headers);
    }

    /**
     * @return the rows
     */
    public List<CanteenItem> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<CanteenItem> rows) {
        this.rows = rows;
    }
}
