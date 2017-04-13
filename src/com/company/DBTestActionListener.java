package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DBTestActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            JFrame frame = WindowService.createWindow("Test connection", JFrame.DISPOSE_ON_CLOSE, 500, 100);

            String connectionStatus = "Failed to connect to database";
            java.awt.Color color = java.awt.Color.RED;
            if (conn != null && !conn.isClosed()) {
                color = java.awt.Color.GREEN;
                connectionStatus = "Successfully established connection to the database";
            }

            JLabel connectionStatusLabel = new JLabel(connectionStatus);
            connectionStatusLabel.setForeground(color);
            frame.add(connectionStatusLabel);

            frame.setVisible (true);
        } catch (Exception ex) {}
    }
}
