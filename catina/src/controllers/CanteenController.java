package controllers;

import dao.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import main.Environment;
import models.Canteen;
import models.ItemOnSale;
import models.Sale;
import models.SoldItem;
import view.canteen.CanteenView;

import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.codec.binary.Base64;
import java.util.Locale;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.swing.JRViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import view.report.ReportView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinicius Jimenez
 */
public class CanteenController {

    private final DAO itemDAO;
    private final DAO itemSoldDAO;
    private final DAO saleDAO;
    private final CanteenView view;

    public CanteenController() {
        this.itemDAO = new DAO(ItemOnSale.class);
        this.itemSoldDAO = new DAO(SoldItem.class);
        this.saleDAO = new DAO(Sale.class);

        var manager = Environment.getUser();
        var canteen = manager.getCanteen();

        Environment.setCurrentCanteen(canteen);
        var items = (ArrayList) this.itemDAO
                .get()
                .stream()
                .map(item -> (ItemOnSale) item)
                .filter(item -> item.getCanteen().getId().equals(canteen.getId()))
                .collect(Collectors.toList());

        this.view = new CanteenView(canteen, items);        
    }

    public void registerItem(String name, double price, int qtty, String type) {
        var item = new ItemOnSale(name, price, type, Environment.getCurrentCanteen().getId(), qtty);
        this.itemDAO.post(item);

        this.syncItems();
    }

    public void editItem(ItemOnSale canteenItem, String name, double price, int qtty) {        
        canteenItem.setName(name);
        canteenItem.setPrice(price);
        canteenItem.setQuantity(qtty);

        this.itemDAO.put(canteenItem);        
        this.syncItems();
    }

    public void registerSale(ArrayList<ItemOnSale> items, HashMap<ItemOnSale, Integer> itemsQtty) {
        Sale sale = new Sale(Environment.getCurrentCanteen().getId());
        sale.setTotalCost(itemsQtty);
        this.saleDAO.post(sale);

        items.forEach(item -> {
            SoldItem soldItem = new SoldItem(item, itemsQtty.get(item), sale);
            this.itemSoldDAO.post(soldItem);
            this.editItem(item, item.getName(), item.getPrice(), (int) item.getQuantity() - itemsQtty.get(item));
        });
        this.syncItems();
    }

    public void viewProfit() {  
        var salesData = this.saleDAO
        .get()
        .stream()
        .map(sale -> (Sale) sale)
        .filter(sale -> sale.getCanteen().getId().equals(Environment.getCurrentCanteen().getId()))
        .collect(Collectors.toList());              
        
        var allSoldItems = this.itemSoldDAO
        .get()
        .stream()
        .map(item -> (SoldItem) item)                
        .collect(Collectors.toList());
        
        var saleQuantity = new HashMap <Sale, Integer>();        
        salesData.forEach(sale -> {
            saleQuantity.put(sale, 0);
            allSoldItems.forEach(item -> {
               if(item.getSale().getId().equals(sale.getId())){
                   int newValue = (int) ((int) saleQuantity.get(sale) + item.getQuantity());                   
                    saleQuantity.put(sale, newValue);
               }
            });
        });        
                
        
        var report = new SalesReport(saleQuantity);        
        var reportView = new ReportView(report.create());
        
    }     

    public void init() {
        this.view.setRegisterItemHandler(data -> {
            var name = (String) data.get("name");
            var price = (Double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var type = (String) data.get("type");            
            this.registerItem(name, price, qtty, type);
        });
        
        this.view.setEditItemHandler(data -> {            
            var name = (String) data.get("name");
            var price = (double) data.get("price");
            var qtty = (Integer) data.get("qtty");
            var item = (ItemOnSale) data.get("canteenItem");            
            this.editItem(item, name, price, qtty);
        });
        
        this.view.setSaleRegisterHandler(data -> {                     
            var items = (ArrayList<ItemOnSale>) data.get("items");
            var itemsQtty = (HashMap<ItemOnSale, Integer>) data.get("itemsQtty");
            this.registerSale(items, itemsQtty);                                                
        });
        
        this.view.setViewProfitHandler(data -> {
            this.viewProfit();
        });
    }   
    
    private void syncItems(){
        var itemsNow = this.itemDAO
                .get()
                .stream()
                .map(item -> (ItemOnSale) item)
                .filter(item -> item.getCanteen().getId().equals(Environment.getCurrentCanteen().getId()))
                .collect(Collectors.toList());
                
        this.view.syncItems(itemsNow);
    }        

}
