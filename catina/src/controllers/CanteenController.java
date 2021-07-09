package controllers;

import java.util.ArrayList;
import models.Canteen;
import models.CanteenItem;
import models.Item;
import view.canteen.CanteenView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius Jimenez
 */
public class CanteenController implements AppController{
    CanteenView view;
    
    public CanteenController(){
        this.view = new CanteenView();
    }
    

    @Override
    public void init() {
        System.out.println("Oi");
    }

}
