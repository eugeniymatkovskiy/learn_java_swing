package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SelectActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = WindowService.createWindow("Select students", JFrame.DISPOSE_ON_CLOSE, 500, 500);
        frame.setLayout(new GridLayout(6, 1));

        Map<String, Integer> searchFields = new LinkedHashMap<String, Integer>(){{
            put("ID", 6);
        }};
        searchFields.putAll(UserService.getStudentInputFields());

        searchFields.forEach((n, i) -> WindowService.addFieldToTheFrame(frame, n, i, ""));

        WindowService.addButton(frame, "Search", new ShowStudentsActionListener());

        frame.setVisible(true);
    }
}
