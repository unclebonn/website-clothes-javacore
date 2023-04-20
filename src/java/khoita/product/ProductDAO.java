/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import khoita.detailcart.DetailCartDTO;
import khoita.login.LoginDTO;
import khoita.registration.RegistrationDTO;
import khoita.utils.DBHelper;

/**
 *
 * @author Fstore
 */
public class ProductDAO implements Serializable {

    Map<String, ProductDTO> product;

    public Map<String, ProductDTO> getProduct() {
        return product;
    }

    public void getProductFromSQL() throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "SELECT * FROM PRODUCT";
                stm = conn.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String productDescription = rs.getString("productDescription");
                    float productPrice = rs.getFloat("productPrice");
                    int productQuantity = rs.getInt("productQuantity");

                    ProductDTO product_info = new ProductDTO(productID, productName, productDescription, productPrice, productQuantity);
                    if (product == null) {
                        product = new HashMap<>();
                    }

                    product.put(productID, product_info);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void checkCartExisted(String account) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //    String randomID = "FLOOR(RAND()*(10000-5+1)+5)"; // make a random ID from 5 - 10000

        try {
            // select cartID of that User if there cartID is existed
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "SELECT cartID FROM Cart WHERE account = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account);
                rs = stm.executeQuery();
                // if they are not existed the system is gonna create a new cart for them
                if (!rs.next()) {
                    Connection conn2 = null;
                    PreparedStatement stm2 = null;
                    try {
                        conn2 = DBHelper.connect();
                        if (conn2 != null) {
                            String sql2 = "INSERT INTO Cart(cartID,account) VALUES (FLOOR(RAND()*(10000-5+1)+5),?)";
                            stm2 = conn.prepareStatement(sql2);
                            stm2.setString(1, account);
                            stm2.executeUpdate();
                        }
                    } finally {
                        if (stm2 != null) {
                            stm.close();
                        }
                        if (conn2 != null) {
                            conn.close();
                        }
                    }

                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    List<DetailCartDTO> productUser;

    public List<DetailCartDTO> getProductFromCart() {
        return productUser;
    }

    //done function do not fix
    public void getProductFromCartSQL(String account) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        ProductDTO product = null;

        try {
            conn = DBHelper.connect2();
            if (conn != null) {
                String sql = "SELECT * FROM DetailCart WHERE cartID = (SELECT cartID FROM Cart WHERE account = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String productDescription = rs.getString("productDescription");
                    float productPrice = (float) (Math.round(rs.getFloat("productPrice")*10.0)/10.0);
                    int productQuantity = rs.getInt("productQuantity");
                    String cartID = rs.getString("cartID");
                    String productProcess = rs.getString("process");
                    DetailCartDTO product = new DetailCartDTO(productID, productName, productDescription, productPrice, productQuantity, cartID, productProcess);

                    if (productUser == null) {
                        productUser = new ArrayList<>();
                    }

                    productUser.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //this function has done tomorrow we gonna apply this function to addToCart function.
    public boolean checkItemsIsExistedInUserCart(ProductDTO product, String account) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "SELECT productID,productPrice,productQuantity FROM DetailCart WHERE cartID = (SELECT cartID FROM Cart WHERE account = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account);

                rs = stm.executeQuery();
                while (rs.next()) {
                    // compare if the items has existed in the cart 
                    if (rs.getString("productID").equals(product.getProductID())) {
                        int productQuantity = product.getProductQuantity();
                        float productPrice = rs.getFloat("productPrice") + (product.getProductPrice() * productQuantity);
                        productQuantity = rs.getInt("productQuantity") + product.getProductQuantity();

                        //update their quantity and price depend on their quantity
                        Connection conn2 = null;
                        PreparedStatement stm2 = null;
                        try {
                            conn2 = DBHelper.connect();
                            if (conn2 != null) {
                                String sql2 = "UPDATE DetailCart"
                                        + "  SET	productPrice = ? ,"
                                        + "		productQuantity = ? "
                                        + "  WHERE      cartID = (SELECT cartID FROM Cart WHERE account = ?) AND productID = ?";

                                stm2 = conn.prepareStatement(sql2);
                                stm2.setFloat(1, productPrice);
                                stm2.setInt(2, productQuantity);
                                stm2.setString(3, account);
                                stm2.setString(4, product.getProductID());

                                if (stm2.executeUpdate() > 0) {
                                    flag = true;
                                }
                            }
                        } finally {
                            if (stm2 != null) {
                                stm2.close();
                            }
                            if (conn2 != null) {
                                conn2.close();
                            }
                        }
                    }
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return flag;
    }

    public boolean addToCart(ProductDTO product, String account) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean flag = false;
        ProductDAO dao = new ProductDAO();

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                dao.checkCartExisted(account);
                if (!dao.checkItemsIsExistedInUserCart(product, account)) {
                    String sql = "INSERT INTO DetailCart (productID,productName,productDescription,productPrice,productQuantity,cartID,process)"
                            + "VALUES"
                            + "(?,?,?,?,?,(SELECT cartID FROM Cart WHERE account = ?),'pending'"
                            + ")";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, product.getProductID());
                    stm.setString(2, product.getProductName());
                    stm.setString(3, product.getProductDescription());
                    stm.setFloat(4, product.getProductPrice());
                    stm.setInt(5, product.getProductQuantity());
                    stm.setString(6, account);

                    if (stm.executeUpdate() > 0) {
                        flag = true;
                    }
                } else {
                    flag = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return flag;
    }
}
////////////////////////////////////////////    CODE ABOVE FOR 'ADD TO CART' FUNCTION FOR USER ONLINE  ///////////////////////////////////////////////////////////////

