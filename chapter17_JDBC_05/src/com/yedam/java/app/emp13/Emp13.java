package com.yedam.java.app.emp13;

public class Emp13 {

 	private int employeeId;
	private String firstName; 
	private String jobId; 
	private int  salary;
	private String commissionPst;
	private String departmentName;
	private int  locationId;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getCommissionPst() {
		return commissionPst;
	}
	public void setCommissionPst(String commissionPst) {
		this.commissionPst = commissionPst;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	@Override
	public String toString() {
		return "Emp13 [employeeId=" + employeeId + ", firstName=" + firstName + ", jobId=" + jobId + ", salary="
				+ salary + ", commissionPst=" + commissionPst + ", departmentName=" + departmentName + ", locationId="
				+ locationId + "]";
	}
	
	
	
	
}
