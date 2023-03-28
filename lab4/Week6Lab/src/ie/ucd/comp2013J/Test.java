package ie.ucd.comp2013J;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        //Test getAllEmployees()
        List<Employee> employees = EmployeeDAO.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee.toString() + "\n");
        }

        System.out.println("------------------------------------------------------------------------");

        // Q2 Test
        Employee leo = EmployeeDAO.getEmployeeByID(6542);
        System.out.println(leo);

        System.out.println("------------------------------------------------------------------------");

        // Q3 Test
        boolean success = EmployeeDAO.deleteEmployeeByID(2525);  // delete peter
        Employee peter = EmployeeDAO.getEmployeeByID(2525);
        System.out.println("Peter is: " + peter);

        System.out.println("------------------------------------------------------------------------");

        // Q4 Test
        Employee newLeo = new Employee(6542, "Leo", "Yang", "Driver", 10, 5);
        EmployeeDAO.updateEmployee(newLeo);
        System.out.println(EmployeeDAO.getEmployeeByID(6542));

        System.out.println("------------------------------------------------------------------------");

        // Q5 Test
        Employee kelvin = new Employee(100103, "Kelvin", "Qiu", "Developer", 10000, 0);
        EmployeeDAO.insertEmployee(kelvin);
        System.out.println(EmployeeDAO.getEmployeeByID(100103));
    }

}
