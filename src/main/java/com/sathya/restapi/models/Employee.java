package com.sathya.restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max =50, message = "Name should be between 2 and 50 and 50 charecter")
	private String name;
	
	@Positive(message = "salary must be greater than 0")
	private double salary;
	
	@NotBlank(message = "department is required")
	@Size(max =30, message = "Department name should not exceed 30 characters")
	private String department;
	
	@NotBlank(message = "Gender is required")
	@Pattern(regexp ="Male|Female|Others", message = "Gender must be male, Female, or, others")
	private String gender;
	
	 @NotBlank(message = "Email is required")
	    private String email;
	
	
	
	
	
	
	
}
