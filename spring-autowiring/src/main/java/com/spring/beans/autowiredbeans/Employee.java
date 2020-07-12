package com.spring.beans.autowiredbeans;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.beans.Department;

public class Employee {

	private int empId;
	private String empName;

	@Autowired
	private Department department;

	public Employee() {
		System.out.println("Employee created via default constructor");
	}

	public Employee(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;
		
		System.out.println("Employee created via parameterised constructor");
	}

	public int getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", department=" + department + "]";
	}
}
