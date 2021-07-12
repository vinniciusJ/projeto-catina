package controllers;

import java.util.ArrayList;
import models.CanteenItem;
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
        this.view = new CanteenView(new ArrayList<CanteenItem>());
    }
    

    @Override
    public void init() {
        System.out.println("Oi");
    }

}
