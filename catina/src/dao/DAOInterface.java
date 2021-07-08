/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author Dyogo
 */
public interface DAOInterface {
    public void delete(Object object);
    public ArrayList<Object> get();    
    public void post(Object object);
    public void put (Object oldObject, Object newObject);
}
