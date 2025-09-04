package com.kh.model.dto;

public class EmployeeInfoDto {

	private String empId;
	private int salary;
	private String deptCode;
	private String jobCode;
	public EmployeeInfoDto() {
		super();
	}
	
	public EmployeeInfoDto(String empId, int salary, String deptCode, String jobCode) {
		super();
		this.empId = empId;
		this.salary = salary;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	@Override
	public String toString() {
		return "EmployeeInfoDto [salary=" + salary + ", deptCode=" + deptCode + ", jobCode=" + jobCode + "]";
	}
	
	
}
