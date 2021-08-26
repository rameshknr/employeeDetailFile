package com.employee.employee.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.dto.EmployeeDto;
import com.employee.employee.pojo.Employee;
import com.employee.employee.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("")
	public EmployeeDto fetchAllEmployee(@RequestParam(required = false) String type,@RequestParam(required = false) String fromValue,@RequestParam(required = false) String toValue) throws IOException, ParseException{
		if(type != null) {
			return employeeService.fetchEmployeeByType(type,fromValue,toValue);
		}else {
			return employeeService.fetchAllEmployees();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee,@RequestParam(required = false) String position,@RequestParam(required = false) int index) throws IOException, ParseException{
		 employeeService.addEmployee(employee,position,index);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) throws IOException, ParseException{
		 employeeService.updateEmployee(employee);
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteEmployee(@RequestBody Employee employee) throws IOException, ParseException{
		 employeeService.deleteEmployee(employee);
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/generate-file")
	public ResponseEntity<Void> generateFileEmployee() throws IOException{
		employeeService.generateFileEmployee();
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
