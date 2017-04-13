package com.company;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static final String QUERY_FOR_GET_ALL_USERS = "SELECT * FROM users";

    public static List<User> getStudentsByQuery(String query){
        Statement state = DBConnection.getInstance().getStatement();
        List<User> listOfUsers = new ArrayList<>();
        if (state == null) {
            return listOfUsers;
        }

        try {
            ResultSet res = state.executeQuery(query);
            while (res.next()) {
                listOfUsers.add(User.createUserFromTableRecord(res));
            }
        } catch (Exception e) {}

        return listOfUsers;
    }

    public static boolean insertUpdateQuery(String query) {
        Statement state = DBConnection.getInstance().getStatement();
        Boolean statusOfExecute = false;
        try{
            if (state.executeUpdate(query) > 0) {
                statusOfExecute = true;
            }
        }catch (Exception e){}

        return statusOfExecute;
    }

    public static boolean deleteQuery(String query) {
        Statement stmt = DBConnection.getInstance().getStatement();
        try{
            if (stmt != null) {
                return !stmt.execute(query);
            }
        }catch (Exception e){}

        return false;
    }
}
