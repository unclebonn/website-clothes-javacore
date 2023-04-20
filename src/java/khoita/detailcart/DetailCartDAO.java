/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.detailcart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import khoita.utils.DBHelper;

/**
 *
 * @author Fstore
 */
public class DetailCartDAO implements Serializable {

    public boolean deleteItemFromCart(String productID, String cardID) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean flag = false;

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "DELETE FROM DetailCart WHERE productID = ? AND cartID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setString(2, cardID);

                if (stm.executeUpdate() > 0) {
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

    public boolean updateItemFromCart(String productID, String cartID, int quantity) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean flag = false;

        try {
            conn = DBHelper.connect2();
            if (conn != null) {
                String sql = "UPDATE DetailCart SET productQuantity = ? WHERE productID = ? AND cartID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, productID);
                stm.setString(3, cartID);

                if (stm.executeUpdate() > 0) {
                    System.out.println("qua duoc vong dau");
                    Connection conn2 = null;
                    PreparedStatement stm2 = null;
                    try {
                        conn2 = DBHelper.connect2();
                        if (conn2 != null) {
                            System.out.println("vo duoc toi day");
                            //lay gia tien cua san pham cap nhat lai tong gia ma so luong san pham moi duoc cap nhat
                            String sql2 = "UPDATE DetailCart SET productPrice =(SELECT productQuantity FROM DetailCart WHERE productID = ? AND cartID = ? )*(SELECT productPrice FROM Product WHERE productID = ?) WHERE productID = ? AND cartID = ? ";
                            stm2 = conn2.prepareStatement(sql2);
                            stm2.setString(1, productID);
                            stm2.setString(2, cartID);
                            stm2.setString(3, productID);
                            stm2.setString(4, productID);
                            stm2.setString(5, cartID);

                            if (stm2.executeUpdate() > 0) {
                                flag = true;
                            }
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
