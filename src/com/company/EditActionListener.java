package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        JScrollPane scrollPane = (JScrollPane)((Component)((Component)e.getSource()).getParent().getParent().getComponents()[1]);
        JTable table = ((JTable)scrollPane.getViewport().getView());
        int userId = (Integer)table.getValueAt(table.getSelectedRow(), 0);

        List<User> users = UserRepository.getStudentsByQuery("SELECT * FROM users WHERE id=" + userId);

        if (users.isEmpty()) {
            return;
        }

        User selectedUser = users.get(0);

        JFrame listOfStudentsFrame = WindowService.getRootElement(e);
        listOfStudentsFrame.dispose();

        JFrame frame = WindowService.createWindow("Edit student", JFrame.DISPOSE_ON_CLOSE, 500, 350);
        frame.setLayout(new GridLayout(5, 1));

        UserService.getStudentInputFields().forEach((n, i) -> WindowService.addFieldToTheFrame(frame, n, i, WindowService.getFieldValue(selectedUser, n).toString()));

        WindowService.addButton(frame, "Save", new SaveChangesActionListener(selectedUser));
        frame.setVisible(true);
    }
}
