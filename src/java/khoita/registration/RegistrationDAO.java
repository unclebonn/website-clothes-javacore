/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoita.utils.DBHelper;

/**
 *
 * @author Fstore
 */
public class RegistrationDAO {

    public boolean register(RegistrationDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "INSERT INTO Registration (account,password,fullname,role) VALUES (?,?,?,0)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getAccount());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFullname());

                int effectRow = stm.executeUpdate();

                if (effectRow > 0) {
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

    private List<RegistrationDTO> listuser;

    public List<RegistrationDTO> getuser() {
        return listuser;
    }

    public void searchFullName(String fullname) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        conn = DBHelper.connect();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM Registration WHERE fullname LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + fullname + "%");

                rs = stm.executeQuery();

                while (rs.next()) {
                    String user_username = rs.getString("account");
                    String user_password = rs.getString("password");
                    String user_fullname = rs.getString("fullname");
                    boolean user_role = rs.getBoolean("role");

                    RegistrationDTO user = new RegistrationDTO(user_username, user_password, user_fullname, user_role);
                    if (this.listuser == null) {
                        listuser = new ArrayList<>();
                    }

                    listuser.add(user);
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

    
    public boolean deleteUser(String accountID) throws ClassNotFoundException, SQLException, NamingException{
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stm = null;
        
        try{
        conn = DBHelper.connect();
        if(conn != null){
            String sql = "DELETE FROM Registration WHERE account = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountID);
            
            if(stm.executeUpdate() > 0){
                flag = true;
            }
        }
        }
        finally{
            if(stm != null){
                stm.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return flag;
    }
    
    public boolean updateUser(String account, String password, String fullname, String lastAccount, boolean isRole) throws ClassNotFoundException, SQLException, NamingException{
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stm = null;
        
        conn = DBHelper.connect();
        try{
            if(conn != null){
                String sql = "UPDATE Registration SET account = ?, password = ?, fullname = ?, role = ? WHERE account = ?";
                       
               
                
                
                stm = conn.prepareStatement(sql);
                stm.setString(1, account);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, isRole);
                stm.setString(5, lastAccount);
                
                if(stm.executeUpdate() > 0){
                    flag = true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return flag;
    }
}
