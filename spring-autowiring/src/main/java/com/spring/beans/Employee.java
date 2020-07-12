package com.spring.beans;

public class Employee {

	private int empId;
	private String empName;
	private Department department;

	public Employee() {
		System.out.println("Employee created via default constructor");
	}

	public Employee(Department department) {
		this.department = department;
		
		System.out.println("Employee created via department parameterised constructor");
	}

	public Employee(int empId, String empName, Department department) {
		this.empId = empId;
		this.empName = empName;
		this.department = department;
		
		System.out.println("Employee created via parameterised constructor");
	}

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", department=" + department + "]";
	}
}
