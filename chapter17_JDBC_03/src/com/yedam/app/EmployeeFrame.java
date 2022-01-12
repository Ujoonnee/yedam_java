package com.yedam.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.app.employee.Employee;
import com.yedam.app.employee.EmployeeDAO;
import com.yedam.app.employee.EmployeeDAOImpl;

public class EmployeeFrame {

	private EmployeeDAO dao = EmployeeDAOImpl.getInstance();
	private Scanner sc = new Scanner(System.in);

	public EmployeeFrame() {
		while (true) {
			// 메뉴 출력
			menuPrint();

			// 메뉴 선택
			int menuNo = menuSelect();

			// 각 메뉴별 기능 실행
			if (menuNo == 1) {
				insertEmployee();
			} else if (menuNo == 2) {
				updateEmployee();
			} else if (menuNo == 3) {
				deleteEmployee();
			} else if (menuNo == 4) {
				selectOne();
			} else if (menuNo == 5) {
				selectAll();
			} else if (menuNo == 9) {
				end();
				break;
			}

		}
	}
	
	public void menuPrint() {
		System.out.println();
		System.out.println("=== 1.등록  2.수정  3.삭제  4.사원조회  5.전체조회  9.종료 ===");
		System.out.println("선택> ");
	}
	
	public int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = sc.nextInt();
		} catch (Exception e) {
			System.out.println("없는 메뉴입니다.");
		}
		return menuNo;
	}
	
	public void insertEmployee() {
		Employee emp = inputEmployeeInfo();
		dao.insert(emp);
	}
	
	public void updateEmployee() {
		Employee emp = inputEmployeeSalary(); 
		dao.update(emp);
	}
	
	public void deleteEmployee() {
		dao.delete(inputEmployeeId());
	}
	
	public void selectOne() {
		System.out.println("사번> ");
		int employeeId = sc.nextInt();
		Employee emp = dao.selectOne(employeeId);
		System.out.println(emp);
	}
	
	public void selectAll() {
		List<Employee> list = dao.selectAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	public void end() {
		System.out.println("프로그램 종료");
	}
	
	public Employee inputEmployeeInfo() {
		Employee emp = new Employee();
		System.out.println("사번> ");
		emp.setEmployeeId(sc.nextInt());
		System.out.println("이름> ");
		emp.setFirstName(sc.next());
		System.out.println("성> ");
		emp.setLastName(sc.next());
		System.out.println("이메일> ");
		emp.setEmail(sc.next());
		System.out.println("전화번호> ");
		emp.setPhoneNumber(sc.next());
		System.out.println("입사일> ");
		emp.setHireDate(sc.next());
		System.out.println("직급> ");
		emp.setJobId(sc.next());
		System.out.println("연봉> ");
		emp.setSalary(sc.nextInt());
		System.out.println("상여> ");
		emp.setCommissionPct(sc.next());
		System.out.println("상사> ");
		emp.setManagerId(sc.next());
		System.out.println("부서> ");
		emp.setDepartmentId(sc.next());
		
		return emp;
	}
	
	public Employee inputEmployeeSalary() {
		Employee emp = new Employee();
		emp.setEmployeeId(inputEmployeeId());
		System.out.println("연봉> ");
		emp.setSalary(sc.nextInt());
		
		return emp;
	}
	
	public int inputEmployeeId() {
		System.out.println("사번> ");
		int employeeId = sc.nextInt();
		return employeeId;
	}
}
