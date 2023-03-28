package ie.ucd.comp2013J;

import java.sql.*;

public class JDBCTool {
    public static Connection getConnection(String url, String dbname, String username, String password) throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" +
                dbname + "?" + "user=" +
                username + "&password=" + password);
        return conn;
    }

    public static Connection getConnection() throws SQLException {
        return JDBCTool.getConnection("localhost:3306", "employee", "root", "");
    }

    public static void closeAllConnections(Connection connect, Statement st, ResultSet result) throws SQLException {
        if (connect != null) {
            connect.close();
        }
        if (st != null) {
            st.close();
        }
        if (result != null) {
            result.close();
        }
    }
}
