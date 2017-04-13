package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class UserService {

    public static final String[] DEFAULT_COLUMN_NAMES = {
            "Id",
            "Name",
            "Birthday",
            "Phone",
            "Email"
    };

    public static Object[][] prepareStudentsToTableFormat(List<User> listOfStudents, List<String> fields) {
        List<Object[]> records = new ArrayList<>();

        listOfStudents.forEach(s -> {
            List<Object> studentInfo = new ArrayList<>();
            fields.forEach(f -> studentInfo.add(WindowService.getFieldValue(s, f)));
            records.add(studentInfo.toArray());
        });

        Object[][] result = new Object[records.size()][];

        return records.toArray(result);
    }

    public static Map<String, Integer> getStudentInputFields() {
        return new LinkedHashMap<String, Integer>() {{
            put("Name", 30);
            put("Birthday", 15);
            put("Phone", 15);
            put("Email", 30);
        }};
    }

    public static Map<String, String> getDataFromFields(java.util.List<Component> components) {
        Map<String, String> userData = new LinkedHashMap<>();
        components.forEach(c -> {
            if (!(c instanceof JPanel)) {
                return;
            }

            AtomicReference<String> column = new AtomicReference<>();
            AtomicReference<String> value = new AtomicReference<>();
            AtomicBoolean isNotEmpty = new AtomicBoolean();

            Arrays.asList(((JPanel) c).getComponents()).forEach(re -> {
                if (re instanceof JLabel) {
                    column.set(((JLabel) re).getText().toLowerCase());
                } else if(re instanceof JTextField) {
                    if (((JTextField) re).getText().length() > 0) {
                        isNotEmpty.set(true);
                        value.set(((JTextField) re).getText());
                    }
                }
            });

            if (isNotEmpty.get()) {
                userData.put(column.get(), value.get());
            }
        });

        return userData;
    }
}
