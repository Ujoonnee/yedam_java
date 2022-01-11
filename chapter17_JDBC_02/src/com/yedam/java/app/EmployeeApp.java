package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

public class EmployeeApp {

	public static void main(String[] args) {

		boolean run = true;
		Scanner sc = new Scanner(System.in);
		EmployeesDAO dao = EmployeesDAO.getInstance();

		Employee emp = null;
		int employeeId = 0;

		while (run) {
			System.out.println();
			System.out.println("=== 1.등록  2.수정  3.삭제  4.사원조회  5.전체조회  9.종료 ===");
			System.out.println("선택> ");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				emp = new Employee();
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
				
				dao.insert(emp);
				break;
			case 2:
				emp = new Employee();
				System.out.println("사번> ");
				emp.setEmployeeId(sc.nextInt());
				System.out.println("연봉> ");
				emp.setSalary(sc.nextInt());
				
				dao.update(emp);
				break;
			case 3:
				System.out.println("사번> ");
				employeeId = sc.nextInt();
				dao.delete(employeeId);
				break;
			case 4:
				System.out.println("사번> ");
				employeeId = sc.nextInt();
				emp = dao.selectOne(employeeId);
				System.out.println(emp);
				break;
			case 5:
				List<Employee> list = dao.selectAll();
				for (Employee employee : list) {
					System.out.println(employee);
				}
				break;
			case 9:
				run = false;
				System.out.println("프로그램 종료");
				break;
			}
		}
		
		sc.close();
	}

}
