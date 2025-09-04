package com.kh.model.dto;

import java.sql.Date;

public class EmployeeDto {

	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private String deptCode;
	private String jobCode;
	private String salLevel;
	private int salary;
	private double bonus;
	private String managerId;
	private Date hireDate;
	private Date entDate;
	private String entYN;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, String managerId, Date hireDate, Date entDate,
			String entYN) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.entYN = entYN;
	}

	
	public String getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public String getSalLevel() {
		return salLevel;
	}

	public int getSalary() {
		return salary;
	}

	public double getBonus() {
		return bonus;
	}

	public String getManagerId() {
		return managerId;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public Date getEntDate() {
		return entDate;
	}

	public String getEntYN() {
		return entYN;
	}

	@Override
	public String toString() {
		return "EmployeeDto [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel
				+ ", salary=" + salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate=" + hireDate
				+ ", entDate=" + entDate + ", entYN=" + entYN + "]";
	}

}
