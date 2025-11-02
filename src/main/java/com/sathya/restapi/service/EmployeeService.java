package com.sathya.restapi.service;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.restapi.exceptions.EmployeeNotFoundException;
import com.sathya.restapi.models.Employee;
import com.sathya.restapi.repository.Repository;


	@Service
 	public class EmployeeService 
	{
	
	@Autowired	
	Repository employeeRepository;
	
	public Employee serviceMethod(Employee employee) 
	{
		
		
		//post mapping
		
		//1.check department
		if(!employee.getDepartment().equalsIgnoreCase("Hr") && !employee.getDepartment().equalsIgnoreCase("Test") && !employee.getDepartment().equalsIgnoreCase("Dev") )
		{
			throw new RuntimeException("department is not there");
		}
		
		//2.
		if (employee.getDepartment().equalsIgnoreCase("hr") || 
			    employee.getDepartment().equalsIgnoreCase("tester") || 
			    employee.getDepartment().equalsIgnoreCase("dev"))
		{

			    String department = employee.getDepartment();
			    String formattedString = department.substring(0, 1).toUpperCase()   // first char uppercase
			                             + department.substring(1).toLowerCase();  // rest lowercase
			    
			    employee.setDepartment(formattedString);
		}
		
		//3.
		if(employee.getSalary() > 30000)
		{
			employee.setSalary(employee.getSalary() + 500);
		}
		
		
		
	
		return employeeRepository.save(employee);
	
	}

	
	//get all employees
	
	public List<Employee> getAllEmployee() 
	{
		List<Employee> employees = employeeRepository.findAll();
		
		return employees;
	}

	
	//get by id
	
	public Employee getEmployeeById(long id)
	{
	Optional<Employee> optionalEmp = employeeRepository.findById(id);
		return optionalEmp.orElseThrow( () -> new EmployeeNotFoundException(" the emp with"+id+"not available"));
	}

	//delete by id
	public void deleteById(long id) 
	{
	    if (employeeRepository.existsById(id)) 
	    {
	        employeeRepository.deleteById(id);
	    } 
	    else 
	    {
	        throw new EmployeeNotFoundException("Employee not found with id " );
	    }
	}

	
	
	//put mapping
	public Employee updatedEmployeeById(Long id, Employee updatedEmployeeDetails) {
	    Optional<Employee> optionalEmp = employeeRepository.findById(id);
	    Employee existingEmp = optionalEmp.orElseThrow(() -> 
	        new EmployeeNotFoundException("emp not found with id " + id)
	    );

	    // update fields
	    existingEmp.setName(updatedEmployeeDetails.getName());
	    existingEmp.setDepartment(updatedEmployeeDetails.getDepartment());
	    existingEmp.setSalary(updatedEmployeeDetails.getSalary());
	    existingEmp.setGender(updatedEmployeeDetails.getGender());
	   

	    // save updated employee
	    return employeeRepository.save(existingEmp);
	}

	
	
	//patch mapping
	public Employee updatedSpecificEmployeeData(Long id, Map<String, Object> updatedDetails) {
		
		//find by id get the existing emp
		Optional<Employee> optionalEmp = employeeRepository.findById(id);
		Employee existingEmp= optionalEmp.orElseThrow( () -> new EmployeeNotFoundException("data not found with id"+id));
	
		//update the existing employee
		updatedDetails.forEach((k, v) -> {
		    switch (k) {
		        case "name": existingEmp.setName((String) v);
		          			break;

		        case "salary":
		            existingEmp.setSalary((Double)v); 
		            		break;

		        case "department": existingEmp.setDepartment((String) v);
		            		break;

		        case "gender": existingEmp.setGender((String) v); // use setter, not getter
		        			break;	

		        default:
		            throw new IllegalArgumentException("Unexpected value: " + k);
		    }
		    
		   
		  });
		return employeeRepository.save(existingEmp);
		
		 
		
		

		

	}

	//save all employees
	public List<Employee> getAllEmployee(List<Employee> saveAllemployees) {
		 List<Employee> allEmployees = employeeRepository.saveAll(saveAllemployees);
		 return allEmployees;
	}

	// count all employees
	public long countAllEmployees() {
		  long countEmployees = employeeRepository.count();
		  return countEmployees;
		  
		  
		  
	}	  
		  
	// Delete all employees
	public void deleteAllEmployees() 
	{
	    employeeRepository.deleteAll();
	   
		
	
	}


	public Employee getEmployeeByEmail(String email) 
	{
		Optional<Employee> optionalEmp = employeeRepository.findByEmail(email);
	    return optionalEmp
	        .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
	}
}

	

	


	
	
	
	

		 
