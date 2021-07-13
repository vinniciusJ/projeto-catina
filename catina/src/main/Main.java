package main;


import controllers.LoginController;
import dao.DAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Item;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dyogo
 */
public class Main {
    
    public static void main(String[] args) {        
        // var loginController = new LoginController();
        Item i = new Item("Batata", 1.75, "Assado", "4231c04b-f8ba-43c5-866e-b5485d453d9f");
        var dao = new DAO(Item.class);
        System.out.println(1.75);
        JSONParser jsonParser = new JSONParser();  
        try {
            var k = jsonParser.parse(i.toString());
            dao.post(i);
            System.out.println(k);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
