package com.sathya.restapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.restapi.models.Employee;
import com.sathya.restapi.service.EmployeeService;

import jakarta.validation.Valid;

	@RestController
	public class Controller
	{
		
//	@GetMapping("/greet")	
//	public String greet()
//	{
//		return "Welcome to REST Apis...";
//	}
		
	@Autowired
	EmployeeService employeeService;
	
	
	
	//post
	@PostMapping("/save")
	public ResponseEntity<Employee> controllerMethod(@RequestBody @Valid Employee employee) 
	{
		Employee saveemployee=employeeService.serviceMethod(employee);
		return ResponseEntity.status(HttpStatus.CREATED)
							.header("info", "data save sucessfuly")
							.body(saveemployee);
							

	}
	
	// get all 
	@GetMapping("/getall")
	public ResponseEntity<List<Employee>> getEmployees()
	{
	    List<Employee> employees = employeeService.getAllEmployee();
	    return ResponseEntity.status(HttpStatus.OK)
	            .header("info", "data fetched successfully")
	            .body(employees);
	}
	
	
	//getById
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id)
	{
		 Employee employee = employeeService.getEmployeeById(id);
		 return ResponseEntity.ok(employee);
	}
	
	
	//delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") long id) 
	{
	    employeeService.deleteById(id);
	    return ResponseEntity.noContent().build(); // 204 No Content
	}
	
	
	//put mapping
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updatedEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployeeDetails )
	{
		Employee employee = employeeService.updatedEmployeeById( id, updatedEmployeeDetails);
		return ResponseEntity.status(HttpStatus.OK)
							 .header("info", "data saved sucessfully")
							 .body(employee);
	}
	
	
	//patch mapping
	@PatchMapping("/{id}")
	public ResponseEntity<Employee> updatedSpecificEmployeeData(@PathVariable("id") Long id, @RequestBody Map<String, Object> updatedDetails)
	{
		Employee employee = employeeService.updatedSpecificEmployeeData(id, updatedDetails);
		return ResponseEntity.status(HttpStatus.OK)
							 .header("info", "the data save sucessfully")
							 .body(employee);
	}
	
	
	//send the multiple employees at a time
	@PostMapping("/saveall")
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestBody List<Employee> saveAllemployees)
	{
		List<Employee> savedEmployees = employeeService.getAllEmployee(saveAllemployees);
		return ResponseEntity.ok(savedEmployees);
							 
	}
	
	
	//count no of employees
	@GetMapping("/count")
	public ResponseEntity<Long> countAllEmployees()
	{
		 long countEmployees = employeeService.countAllEmployees();
		 return ResponseEntity.status(HttpStatus.OK)
				 			  .header("info", "data save sucessfully")
				 			  .body(countEmployees);
	}
	
	
	//delete all employees
	@DeleteMapping("/deleteall")
	public ResponseEntity<Employee> deleteAllEmployees()
	{
		 employeeService.deleteAllEmployees();
		return ResponseEntity.noContent().build();
				 			  
	}
	
	//search by email
	@GetMapping("/search1")
	public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam("email") String email) 
	{ 
		Employee employee=employeeService.getEmployeeByEmail(email);
		return ResponseEntity.ok(employee);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
