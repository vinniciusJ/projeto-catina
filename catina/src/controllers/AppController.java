package controllers;

import main.Environment;
import view.canteen.CanteenView;
import view.manager.ManagerView;
import view.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius Jimenez
 */
public class AppController {
    View view;
    
    public AppController(){
        var hasFullAcess = Environment.getUSER().isFullAcess();
        
        this.view = hasFullAcess ? new ManagerView() : new CanteenView();
    }
}
