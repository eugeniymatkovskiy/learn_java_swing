package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = WindowService.createWindow("Add new student", JFrame.DISPOSE_ON_CLOSE, 500, 350);
        frame.setLayout(new GridLayout(5, 1));

        UserService.getStudentInputFields().forEach((n, i) -> WindowService.addFieldToTheFrame(frame, n, i, ""));

        WindowService.addButton(frame, "Insert", new CreateActionListener());

        frame.setVisible(true);
    }
}
