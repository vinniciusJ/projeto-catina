package controllers;

import dao.DAO;
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
    private final DAO canteenDAO;
    private final CanteenView view;
    
    public CanteenController(){
        this.canteenDAO = new DAO(CanteenItem.class);
        this.view = new CanteenView(new ArrayList<CanteenItem>());

        System.out.println(this.canteenDAO.get());
    }
    

    @Override
    public void init() {
        System.out.println("Oi");
    }

}
