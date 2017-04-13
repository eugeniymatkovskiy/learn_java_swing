package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class UpdateActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = WindowService.createWindow("Update students", JFrame.DISPOSE_ON_CLOSE, 700, 400);
        frame.setLayout(new GridLayout(2, 1));

        WindowService.addButton(frame, "Edit", new EditActionListener());
        JTable table = null;
        try {
            table = new JTable(
                UserService.prepareStudentsToTableFormat(
                        UserRepository.getStudentsByQuery(UserRepository.QUERY_FOR_GET_ALL_USERS),
                        Arrays.asList(UserService.DEFAULT_COLUMN_NAMES)
                ),
                UserService.DEFAULT_COLUMN_NAMES
            );
        }catch (Exception ex) {}


        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
