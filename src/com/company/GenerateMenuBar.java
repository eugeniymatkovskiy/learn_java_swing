package com.company;

import javax.swing.*;

public class GenerateMenuBar {
    public static JMenuBar GetMenu() {
        //set menu layout
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");

        JMenuItem selectItem = new JMenuItem("Select");
        selectItem.addActionListener(new SelectActionListener());
        fileMenu.add(selectItem);

        JMenuItem insertItem = new JMenuItem("Insert");
        insertItem.addActionListener(new InsertActionListener());
        fileMenu.add(insertItem);

        JMenuItem updateItem = new JMenuItem("Update");
        updateItem.addActionListener(new UpdateActionListener());
        fileMenu.add(updateItem);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new DeleteActionListener());
        fileMenu.add(deleteItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem testConnectionItem = new JMenuItem("Test DB connection");
        testConnectionItem.addActionListener(new DBTestActionListener());
        helpMenu.add(testConnectionItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
