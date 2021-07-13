/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.canteen.popups;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author Vinicius Jimenez
 */
public class ViewProfitPopup extends Popup{
    public ViewProfitPopup(String title, Dimension dimension){
        super(title, dimension);
    }
    
    @Override
    public void onSave(Consumer<HashMap<String, Object>> callback) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCancel(Supplier callback) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
