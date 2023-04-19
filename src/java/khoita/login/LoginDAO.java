/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khoita.registration.RegistrationDTO;
import khoita.utils.DBHelper;

/**
 *
 * @author Fstore
 */
public class LoginDAO {

    public RegistrationDTO checkLogin(LoginDTO user) throws ClassNotFoundException, SQLException, NamingException {
        RegistrationDTO regis = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.connect();
            if (conn != null) {
                String sql = "Select account,fullname FROM Registration WHERE account = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getAccount());
                stm.setString(2, user.getPassword());

                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    regis = new RegistrationDTO(user.getAccount(), user.getPassword(), fullname, false);
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
        return regis;

    }
}
