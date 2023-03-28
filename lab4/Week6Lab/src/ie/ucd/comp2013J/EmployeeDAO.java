package ie.ucd.comp2013J;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCTool.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                employees.add(createEmployeeWithResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    //TODO Question 2
    public static Employee getEmployeeByID(int eid) {
        Connection connect = null;
        PreparedStatement st = null;
        ResultSet result = null;
        try {
            connect = JDBCTool.getConnection();
            st = connect.prepareStatement("SELECT * FROM employee WHERE empno = ?;");
            st.setInt(1, eid);
            result = st.executeQuery();
            if (result.next()) {
                return createEmployeeWithResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(connect, st, result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Employee createEmployeeWithResultSet(ResultSet result) throws SQLException {
        int empno = result.getInt("empno");
        String firstname = result.getString("firstname");
        String lastname = result.getString("familyname");
        String job = result.getString("job");
        float salary = result.getFloat("salary");
        int deptno = result.getInt("deptno");
        return new Employee(empno, firstname, lastname, job
                , salary, deptno);
    }

    //TODO Question 3
    public static boolean deleteEmployeeByID(int eid) {
        Connection connect = null;
        PreparedStatement st = null;
        ResultSet result = null;
        try {
            connect = JDBCTool.getConnection();
            st = connect.prepareStatement("DELETE FROM employee WHERE empno = ?");
            st.setInt(1, eid);
            return st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(connect, st, result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //TODO Question 4
    public static boolean updateEmployee(Employee e) {
        Connection connect = null;
        PreparedStatement st = null;
        ResultSet result = null;

        int eid = e.getEmpno();
        String firstname = e.getFirstname();
        String familyname = e.getFamilyname();
        String job = e.getJob();
        float salary = e.getSalary();
        int deptno = e.getDeptno();

        try {
            connect = JDBCTool.getConnection();
            st = connect.prepareStatement("SELECT * FROM employee WHERE empno = ?");
            st.setInt(1, eid);
            result = st.executeQuery();
            if (result.next() && employeeDataChanged(result, firstname, familyname, job, salary, deptno)) {
                st = connect.prepareStatement("UPDATE employee SET firstname = ?, " +
                        "familyname = ?, job = ?, salary = ?, deptno = ? WHERE empno = ?;");
                st.setString(1, firstname);
                st.setString(2, familyname);
                st.setString(3, job);
                st.setFloat(4, salary);
                st.setInt(5, deptno);
                st.setInt(6, eid);
                return st.execute();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(connect, st, result);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private static boolean employeeDataChanged(ResultSet result, String firstname, String familyname, String job, float salary, int deptno) throws SQLException {
        String firstnameDB = result.getString("firstname");
        String familynameDB = result.getString("familyname");
        String jobDB = result.getString("job");
        float salaryDB = result.getFloat("salary");
        int deptnoDB = result.getInt("deptno");
        return !firstname.equals(firstnameDB) || !familyname.equals(familynameDB) || !job.equals(jobDB) || salary != salaryDB || deptno != deptnoDB;
    }

    //TODO Question 5
    public static boolean insertEmployee(Employee e) {
        int eid = e.getEmpno();
        String firstname = e.getFirstname();
        String familyname = e.getFamilyname();
        String job = e.getJob();
        float salary = e.getSalary();
        int deptno = e.getDeptno();

        Connection connect = null;
        PreparedStatement st = null;
        ResultSet result = null;

        try {
            connect = JDBCTool.getConnection();
            st = connect.prepareStatement("INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?);");
            st.setInt(1, eid);
            st.setString(2, firstname);
            st.setString(3, familyname);
            st.setString(4, job);
            st.setFloat(5, salary);
            st.setInt(6, deptno);
            return st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(connect, st, result);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
