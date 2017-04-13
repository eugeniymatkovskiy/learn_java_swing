package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ShowStudentsActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Found students");
        frame.setBounds(530, 500, 700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<String> columnNamesList = new ArrayList<>();

        Map<String, String> searchParameters = new LinkedHashMap<>();
        List<Component> components = Arrays.asList(((Component) e.getSource()).getParent().getParent().getComponents());
        components.forEach(c -> {
            if (!(c instanceof JPanel)) {
                return;
            }

            AtomicReference<String> searchParameter = new AtomicReference<>();
            AtomicReference<String> searchValue = new AtomicReference<>();
            AtomicBoolean isNotEmpty = new AtomicBoolean();

            Arrays.asList(((JPanel) c).getComponents()).forEach(re -> {
                if (re instanceof JCheckBox) {
                    searchParameter.set(((JCheckBox) re).getText().toLowerCase());
                    if (((JCheckBox) re).isSelected()) {
                        columnNamesList.add(((JCheckBox) re).getText().toLowerCase());
                    }
                } else if(re instanceof JTextField) {
                    if (((JTextField) re).getText().length() > 0) {
                        isNotEmpty.set(true);
                        searchValue.set(((JTextField) re).getText());
                    }
                }
            });

            if (isNotEmpty.get()) {
                searchParameters.put(searchParameter.get(), searchValue.get());
            }
        });

        String[] columnNames = UserService.DEFAULT_COLUMN_NAMES;

        if (columnNamesList.size() > 0) {
            columnNames = columnNamesList.toArray(new String[0]);
        }

        JTable table = new JTable(UserService.prepareStudentsToTableFormat(UserRepository.getStudentsByQuery(buildQuery(searchParameters)), Arrays.asList(columnNames)), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private String buildQuery(Map<String, String> searchParameters) {
        String query = UserRepository.QUERY_FOR_GET_ALL_USERS;

        if (searchParameters.size() > 0) {
            List<String> searchCriterias = new ArrayList<>();
            searchParameters.forEach((k, v) -> searchCriterias.add(k + "='" + v +"'"));

            query += " WHERE " + String.join(" AND ", searchCriterias);
        }

        return query;
    }
}
