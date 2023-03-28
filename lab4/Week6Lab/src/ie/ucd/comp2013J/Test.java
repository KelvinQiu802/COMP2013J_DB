package ie.ucd.comp2013J;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		
		//Test getAllEmployees()
		List<Employee> employees = EmployeeDAO.getAllEmployees();
		
		for(int i=0; i<employees.size(); i++) {
			Employee employee = employees.get(i);
			
			System.out.println(employee.toString()+"\n");
		}
		
		//TODO Question 6 
		
		

	}

}
