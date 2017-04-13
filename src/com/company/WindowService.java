package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowService {
    public static void addFieldToTheFrame(JFrame frame, String fieldName, int fieldLength, String fieldValue) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel(fieldName));
        controlPanel.add(new JTextField(fieldValue, fieldLength));

        frame.add(controlPanel);
    }

    public static Object getFieldValue(User user, String field) {
        final String fieldName = field.substring(0,1).toUpperCase() + field.substring(1).toLowerCase();
        Object result = "";
        try{
            result = user.getClass().getMethod("get" + fieldName).invoke(user);
        }catch (Exception e) { e.printStackTrace();}

        return result != null ? result : "" ;
    }

    public static void addButton(JFrame frame, String buttonText, ActionListener actionListener) {
        JButton editButton = new JButton(buttonText);
        editButton.addActionListener(actionListener);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(editButton);

        frame.add(controlPanel);
    }

    public static JFrame getRootElement(ActionEvent e) {
        Container frame = ((Component) e.getSource()).getParent();
        do
            frame = frame.getParent();
        while (!(frame instanceof JFrame));

        return (JFrame) frame;
    }

    public static JFrame createWindow(String name, int onCloseAction, int width, int height) {
        JFrame window = new JFrame(name);//creating instance of JFrame
        window.setDefaultCloseOperation(onCloseAction);
        window.setBounds(500, 350, 400, 500);

        return window;
    }
}
