package ie.ucd.comp2013J;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String firstname = rs.getString("firstname");
                String familyname = rs.getString("familyname");
                String job = rs.getString("job");
                float salary = rs.getFloat("salary");
                int deptno = rs.getInt("deptno");

                Employee e = new Employee(empno, firstname, familyname, job, salary, deptno);

                employees.add(e);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    //TODO Question 2
    public static Employee getEmployeeByID(int eid) {
        return null;
    }

    //TODO Question 3
    public static boolean deleteEmployeeByID(int eid) {
        return false;
    }

    //TODO Question 4
    public static boolean updateEmployee(Employee e) {
        return false;
    }

    //TODO Question 5
    public static boolean insertEmployee(Employee e) {
        return false;
    }
}
