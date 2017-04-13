package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JScrollPane scrollPane = (JScrollPane) ((Component) ((Component) e.getSource()).getParent().getParent().getComponents()[1]);
        JTable table = ((JTable) scrollPane.getViewport().getView());
        int userId = (Integer) table.getValueAt(table.getSelectedRow(), 0);

        showResultOfDelete(UserRepository.deleteQuery("DELETE FROM users WHERE id=" + userId));

        JFrame frame = WindowService.getRootElement(e);
        frame.dispose();
    }

    private void showResultOfDelete(boolean statusOfExecute) {
        if (statusOfExecute) {
            JOptionPane.showMessageDialog(null, "Success! User was deleted!");
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }
}
