/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DAO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JFrame;
import main.Environment;
import models.Sale;
import models.SoldItem;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Vinicius Jimenez
 */
public class SalesReport {
    
    DAO saleDAO;
    DAO itemSoldDAO;
    JSONArray sales;
    String canteenName, username, canteenId, date;
    
    public SalesReport(HashMap <Sale, Integer> saleQuantity){        
        
        var canteen = Environment.getCurrentCanteen();
        var user = Environment.getUser();        
        
        this.canteenName = canteen.getName();
        this.canteenId = canteen.getId();
        this.username = user.getName();
        
        var today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.date = today.format(dtf);
        
        this.sales = new JSONArray();             
        
        saleQuantity.keySet().stream().map((sale) -> {                        
            JSONObject newSale = 
                    this.createObject(sale.getId(), sale.getDateFormatted(), sale.getFormattedTotalCost(), saleQuantity.get(sale));
            return newSale;
        }).forEachOrdered((newSale) -> {
            this.sales.add(newSale);
        });    
    }
    
    public JRViewer create(){                            
        JRViewer reportView = null;
        try {                                    
            JasperReport report;
            JasperPrint jasperPrint;
            Map parameters = new HashMap();     
            
            parameters.put("canteenName", this.canteenName);
            parameters.put("managerName", this.username);
            parameters.put("canteenId", this.canteenId);     
            parameters.put("date", this.date);     
            
            if(this.sales.isEmpty()){                   
                this.sales.add(this.createObject("", "", "", 0));
                report = (JasperReport) JRLoader.loadObject(new File("src/report/EmptySalesReport.jasper"));        
            }
            else{                                      
                report = (JasperReport) JRLoader.loadObject(new File("src/report/SalesReport.jasper"));                
            }
            
            String rawJsonData = this.sales.toJSONString();  
            ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(rawJsonData.getBytes());             
            JsonDataSource ds = new JsonDataSource(jsonDataStream);                                    
            jasperPrint = JasperFillManager.fillReport(report, parameters, ds);                                                             
            reportView = new JRViewer(jasperPrint);                        
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } 
        
        return reportView;
    }
    
    private JSONObject createObject(String saleId, String date, String totalCost, int qttyItems){
        var saleObject = new JSONObject();
        saleObject.put("saleId", saleId);
        saleObject.put("date", date);
        saleObject.put("totalCost", totalCost);
        saleObject.put("qttyItems", qttyItems);             
        return saleObject;
    }
}
