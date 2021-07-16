/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.UUID;

/**
 *
 * @author Dyogo
 */
public abstract class ModelStandart {
    protected String id;

    public String getId() {
        return this.id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }
    
    public abstract String databaseName();
    public abstract String toJSONString();
        
}
