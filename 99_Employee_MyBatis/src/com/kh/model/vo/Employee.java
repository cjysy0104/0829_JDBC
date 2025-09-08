package com.kh.model.vo;

import java.util.Objects;

public class Employee {

	private String empId;
	private String empName;
	private int salary;
	private String deptTitle;
	private String jobName;
	public Employee() {
		super();
	}
	public Employee(String empId, String empName, int salary, String deptTitle, String jobName) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.deptTitle = deptTitle;
		this.jobName = jobName;
	}
	public String getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public int getSalary() {
		return salary;
	}
	public String getDeptTitle() {
		return deptTitle;
	}
	public String getJobName() {
		return jobName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(deptTitle, empName, empId, jobName, salary);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(deptTitle, other.deptTitle) && Objects.equals(empName, other.empName)
				&& Objects.equals(empId, other.empId) && Objects.equals(jobName, other.jobName)
				&& salary == other.salary;
	}
	
	@Override
	public String toString() {
		return "[사번=" + empId + ", 사원명=" + empName + ", 연봉=" + salary + ", 부서명="
				+ deptTitle + ", 직급=" + jobName + "]";
	}
}
