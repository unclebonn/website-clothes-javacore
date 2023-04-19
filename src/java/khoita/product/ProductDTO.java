/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.product;

import java.io.Serializable;

/**
 *
 * @author Fstore
 */
public class ProductDTO  implements Serializable{
    private String productID;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    
    public ProductDTO(){
        
    }

    public ProductDTO(String productID, String productName, String productDescription, float productPrice, int productQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
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

    @Override
    public String toString() {
        return "ProductID=" + productID + ", ProductName=" + productName + ", ProductDescription=" + productDescription + ", ProductPrice=" + productPrice + ", ProductQuantity=" + productQuantity;
    }
    
    
    
}
