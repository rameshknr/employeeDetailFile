package com.employee.employee.dto;

import java.io.Serializable;
import java.util.List;

import com.employee.employee.pojo.Employee;

public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Employee> searchResultList;
	private int totalResultCount;
	public List<Employee> getSearchResultList() {
		return searchResultList;
	}
	public void setSearchResultList(List<Employee> searchResultList) {
		this.searchResultList = searchResultList;
	}
	public int getTotalResultCount() {
		return totalResultCount;
	}
	public void setTotalResultCount(int i) {
		this.totalResultCount = i;
	}
	
	
}
