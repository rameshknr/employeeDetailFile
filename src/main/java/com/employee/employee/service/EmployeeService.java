package com.employee.employee.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employee.employee.dto.EmployeeDto;
import com.employee.employee.pojo.Employee;
import com.employee.employee.util.FileUtil;

@Service
public class EmployeeService {

	private List<Employee> employeeList ;
	
	public EmployeeService() throws IOException {
		this.employeeList = FileUtil.formatCsvtoPojo();
	}

	public EmployeeDto fetchAllEmployees() throws IOException{
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setSearchResultList(employeeList);
		employeeDto.setTotalResultCount(employeeList.size());
	    return employeeDto;	
	}

	public void addEmployee(Employee employee,String position,int index) throws IOException, ParseException {
		if(position != null) {
			if(position.equalsIgnoreCase("beginning")) {
				employeeList.add(0,employee);
			}else if(position.equalsIgnoreCase("end")) {
				employeeList.add(employee);
			}else if(position.contains("specific") && index != 0 ) {
				employeeList.add((index-1),employee);
			}
		}else {
			employeeList.add(employee);
		}
		//FileUtil.addingDataToExcelSheet(employeeList);
	}

	public void updateEmployee(Employee employee) throws IOException, ParseException {
		List<Employee> emp= employeeList.stream().filter(e -> e.getId() != employee.getId()).collect(Collectors.toList());
		emp.add(employee);
		employeeList.clear();
		employeeList.addAll(emp);
		//FileUtil.addingDataToExcelSheet(employeeList);
	}

	public void deleteEmployee(Employee employee) throws IOException, ParseException {
		List<Employee> emp= employeeList.stream().filter(e -> e.getId() != employee.getId()).collect(Collectors.toList());
		employeeList.clear();
		employeeList.addAll(emp);
		//FileUtil.addingDataToExcelSheet(employeeList);
	}

	public EmployeeDto fetchEmployeeByType(String type,String fromValue,String toValue) throws ParseException {
		EmployeeDto employeeDto = new EmployeeDto();
		List<Employee> employeeByType = new ArrayList<>();
		if(type.equalsIgnoreCase("id")) {
			employeeByType = employeeList.stream().filter(e -> e.getId() >= Integer.parseInt(fromValue) && e.getId() <= Integer.parseInt(toValue)).collect(Collectors.toList());
		}else if(type.equalsIgnoreCase("name")) {
			employeeByType = employeeList.stream().filter(e -> e.getName().contains(fromValue) || e.getName().contains(toValue)).collect(Collectors.toList());
		}else if(type.equalsIgnoreCase("doj")) {
			Date fromDate=new SimpleDateFormat("yyyy/MM/dd").parse(fromValue);
			Date toDate=new SimpleDateFormat("yyyy/MM/dd").parse(toValue);
			employeeByType = employeeList.stream().filter(e -> e.getDoj().after(fromDate) && e.getDoj().before(toDate)).collect(Collectors.toList());
		}else if(type.equalsIgnoreCase("age")) {
			employeeByType = employeeList.stream().filter(e -> e.getAge() >= Integer.parseInt(fromValue) && e.getAge() <= Integer.parseInt(toValue)).collect(Collectors.toList());
		}else if(type.equalsIgnoreCase("experience")) {
			employeeByType = employeeList.stream().filter(e -> e.getExperience() >= Integer.parseInt(fromValue) && e.getExperience() <= Integer.parseInt(toValue)).collect(Collectors.toList());
		}
		employeeDto.setSearchResultList(employeeByType);
		employeeDto.setTotalResultCount(employeeByType.size());
	    return employeeDto;
	}

	@SuppressWarnings("static-access")
	public void generateFileEmployee() throws IOException {
		FileUtil.generateSheetSpecific(employeeList.stream().filter(e -> e.getExperience() >= 60).collect(Collectors.toList()));
	}
}
