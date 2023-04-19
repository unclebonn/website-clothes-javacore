/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Fstore
 */
public class DBHelper {

    public static Connection connect() throws ClassNotFoundException, SQLException, NamingException {
        // call driver to connect with database 
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//        //url to database
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJBLOCK3;instanceName=ANHKHOI";
//        // get connectino with all the resources
//        Connection con = DriverManager.getConnection(url, "sa", "12345");

//1.Find Server Context = JNDI - Java Naming Directory Interface
        Context serverContext = new InitialContext();
////2.Find Container Context 
        Context tomcatContext = (Context) serverContext.lookup("java:comp/env");
////3.Get DS 
        DataSource ds = (DataSource) tomcatContext.lookup("HAHA");
////4. Open connection
        Connection con = ds.getConnection();

        return con;

    }

    public static Connection connect2() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJBLOCK3;instanceName=ANHKHOI";
        Connection conn = DriverManager.getConnection(url,"sa","12345");

        return conn;

    }
}
