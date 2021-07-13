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
        var loginController = new LoginController();            
    }
}
