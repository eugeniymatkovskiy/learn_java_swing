package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static DBConnection dbIsntance;
    private static Connection con;
    private static Statement stmt;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (dbIsntance == null) {
            dbIsntance = new DBConnection();
        }
        return dbIsntance;
    }

    public Connection getConnection() {
        if (con == null) {
            try {
                String host = "jdbc:mysql://localhost:3306/learn_java";
                String username = "eugeniy";
                String password = "4B88211ba";
                con = DriverManager.getConnection(host, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }

    public Statement getStatement() {
        if (stmt != null) {
            return stmt;
        }

        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                stmt = conn.createStatement();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error! Something went wrong!");
        } finally {
            return stmt;
        }
    }
}
