package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

public class SaveChangesActionListener implements ActionListener {

    private User user;

    public SaveChangesActionListener(User editingUser) {
        this.user = editingUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        java.util.List<Component> components = Arrays.asList(((Component) e.getSource()).getParent().getParent().getComponents());
        Map<String, String> updatedParameters = UserService.getDataFromFields(components);

        updatedUser(UserRepository.insertUpdateQuery(prepareQueryForInsert(updatedParameters)));
        JFrame frame = WindowService.getRootElement(e);
        frame.dispose();
    }

    private String prepareQueryForInsert(Map<String, String> updateParameters) {
        String query = "UPDATE users SET ";
        List<String> keyValue = new ArrayList();

        updateParameters.forEach((k,v) -> keyValue.add(k.toLowerCase() + "='" + v + "'"));
        query += String.join(", ", keyValue);
        query += " WHERE id=" + this.user.getId();

        return query;
    }

    private void updatedUser(Boolean statusOfExecute) {
        if (statusOfExecute) {
            JOptionPane.showMessageDialog(null, "Success! User was updated!");
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }
}
