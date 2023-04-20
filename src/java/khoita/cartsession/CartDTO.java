/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.cartsession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fstore
 */
public class CartDTO implements Serializable{
    private String customerID;
    private Map<String,Integer> product;

    public CartDTO() {
    }

    public CartDTO(String customerID, Map<String, Integer> product) {
        this.customerID = customerID;
        this.product = product;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Map<String, Integer> getProduct() {
        return product;
    }

    public void setProduct(Map<String, Integer> product) {
        this.product = product;
    }

    public void addItemToCart(String item){
        if(product == null){
            product = new HashMap<>();
        }
        
        int quantity = 1;
        if(product.containsKey(item)){
            quantity = this.product.get(item) + 1;
        }
        
        this.product.put(item, quantity);
        
        
    }
    
    public void deleteItemToCart(String item){
        if(product == null){
            return;
        }
        
        if(this.product.containsKey(item)){
            this.product.remove(item);
            if(this.product.isEmpty()){
                this.product = null;
            }
        }
    }
    
    
    @Override
    public String toString() {
        return "CartDTO{" + "product=" + product + '}';
    }
    
    
}
