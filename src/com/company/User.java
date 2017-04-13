package com.company;

import java.sql.ResultSet;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private Date birthday;
    private String phone;
    private String email;

    private User(int id, String name, Date birthday, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static User createUserFromTableRecord(ResultSet record) {
        User user = null;
        try {
            user = new User(
                    record.getInt("id"),
                    record.getString("name"),
                    record.getDate("birthday"),
                    record.getString("phone"),
                    record.getString("email")
            );
        } catch (Exception e) {
        } finally {
            return user;
        }
    }
}
