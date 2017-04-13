package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CreateActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        java.util.List<Component> components = Arrays.asList(((Component) e.getSource()).getParent().getParent().getComponents());
        Map<String, String> insertParameters = UserService.getDataFromFields(components);

        showResultOfAdded(UserRepository.insertUpdateQuery(prepareQueryForInsert(insertParameters)));

        JFrame frame = WindowService.getRootElement(e);
        frame.dispose();
    }

    private String prepareQueryForInsert(Map<String, String> insertParameters) {
        String query = "INSERT INTO users";

        List<String> columns = new ArrayList<>(insertParameters.keySet());
        List<String> values = new ArrayList<>(insertParameters.values());

        query += "(" + String.join(",", columns) + ")";
        query += " VALUES('" + String.join("','", values) + "')";

        return query;
    }

    private void showResultOfAdded(boolean statusOfExecute) {
        if (statusOfExecute) {
            JOptionPane.showMessageDialog(null, "Success! User was added!");
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }
}
