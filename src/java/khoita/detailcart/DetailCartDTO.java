/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.detailcart;

import java.io.Serializable;

/**
 *
 * @author Fstore
 */
public class DetailCartDTO  implements Serializable{
    private String productID;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private String cardID;
    private String process;
    
    public DetailCartDTO(){
        
    }

    public DetailCartDTO(String productID, String productName, String productDescription, float productPrice, int productQuantity, String process) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.process = process;
    }

    public DetailCartDTO(String productID, String productName, String productDescription, float productPrice, int productQuantity, String cardID, String process) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.cardID = cardID;
        this.process = process;
    }
    
    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getProcess() {
        return process;
    }

    @Override
    public String toString() {
        return "detailcartDTO{" + "productID=" + productID + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", cardID=" + cardID + ", process=" + process + '}';
    }

    public void setProcess(String process) {
        this.process = process;
    }
    
    
    
    
}
