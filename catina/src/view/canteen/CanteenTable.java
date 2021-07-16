/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen;

import java.awt.Dimension;
import java.awt.Font;
import java.util.EventObject;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.ItemOnSale;
import view.ComponentInterface;

/**
 *
 * @author Vinicius Jimenez
 */
public final class CanteenTable extends JTable implements ComponentInterface{
    private final String[] HEADERS = {"Nome: ", "Tipo: ", "Preço: ", "Quantidade: "};
    private Object[][] rows;
    private final DefaultTableModel model;
   
    public CanteenTable(List<ItemOnSale> rows){
        this.rows = CanteenTable.setRowsToObjectMatrix(rows);
        
        this.model = new DefaultTableModel(this.rows, this.HEADERS);
        this.setModel(this.model);
        
        this.init();
    }
    
    private static Object[][] setRowsToObjectMatrix(List<ItemOnSale> rows){
        var convertedRows = new Object[rows.size()][4];
        
        for(int i = 0; i < rows.size(); i++){
            convertedRows[i] = CanteenTable.getCanteenItemsGroupsValues(rows.get(i));
        }
        
        return convertedRows;
    }
    
    private static Object[] getCanteenItemsGroupsValues(ItemOnSale item){
        var name = item.getName();
        var type = item.getType();
        var price = item.getPrice();
        var qtty = item.getQuantity();
        
        return new Object[]{name, type, price, qtty};
    }
    
    public void sync(List<ItemOnSale> rows){
       this.rows = CanteenTable.setRowsToObjectMatrix(rows);
       
       var currentDataModel = (DefaultTableModel) this.getModel();
       
       currentDataModel.setDataVector(this.rows, this.HEADERS);
       currentDataModel.fireTableDataChanged();
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
        return false;
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
    public void paint() {}
}
