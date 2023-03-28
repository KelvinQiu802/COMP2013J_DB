package org.example;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connect = null;
        Statement st = null;
        ResultSet result = null;
        try {
            // 1. Build the Connection
            connect = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            // 2. Create the Statement
            st = connect.createStatement();
            // 2.1 PreparedStatement
            PreparedStatement ps = connect.prepareStatement("SELECT firstname FROM employees WHERE employeeNumber = ?;");
            ps.setInt(1, 1002);  // 1 is the first, sad
            // 3. Execute the SQL
            result = st.executeQuery("SELECT * FROM employees;");
            // 4. Process the ResultSet
            while (result.next()) {
                int employeeNumber = result.getInt("employeeNumber");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                System.out.printf("%d: %s %s\n", employeeNumber, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Close all connections
            try {
                if (connect != null) {
                    connect.close();
                }
                if (st != null) {
                    st.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}