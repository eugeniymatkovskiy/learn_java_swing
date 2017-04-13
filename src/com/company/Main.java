package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame mainWindow = WindowService.createWindow("", JFrame.EXIT_ON_CLOSE, 400, 500);

        mainWindow.setJMenuBar(GenerateMenuBar.GetMenu());

        mainWindow.setLayout(null);//using no layout managers
        mainWindow.setVisible(true);//making the frame visible
    }

}
