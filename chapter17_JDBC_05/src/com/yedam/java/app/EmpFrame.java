package com.yedam.java.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yedam.java.app.emp13.Emp13;
import com.yedam.java.app.emp13.EmpDAO;
import com.yedam.java.app.emp13.EmpDAOImpl;

public class EmpFrame {

	private EmpDAO dao = EmpDAOImpl.getInstance();
	private Scanner sc = new Scanner(System.in);

	public EmpFrame() {

		while (true) {

			int menu = selectMenu();

			if (menu == 1) {
				insertEmp();
			} else if (menu == 2) {
				updateEmp();
			} else if (menu == 3) {
				deleteEmp();
			} else if (menu == 4) {
				selectOne();
			} else if (menu == 5) {
				selectAll();
			} else if (menu == 9) {
				System.out.println("종료");
				break;
			}
			
			
			
		}
	}
	
	public int selectMenu() {
		System.out.println();
		System.out.println("Emp13 > === 1.등록  2.수정  3.삭제  4.단건조회  5.전체조회  9.종료 ===");
		System.out.println("선택> ");
		
		int menu = sc.nextInt();
		return menu;
	}
	
	public void insertEmp() {
		Emp13 emp = new Emp13();
		System.out.println("사번> ");
		emp.setEmployeeId(sc.nextInt());
		System.out.println("이름> ");
		emp.setFirstName(sc.next());
		System.out.println("직무> ");
		emp.setJobId(sc.next());
		System.out.println("연봉> ");
		emp.setSalary(sc.nextInt());
		System.out.println("상여> ");
		emp.setCommissionPst(sc.next());
		System.out.println("부서> ");
		emp.setDepartmentName(sc.next());
		System.out.println("지역> ");
		emp.setLocationId(sc.nextInt());
		
		dao.insert(emp);
	}
	
	public void updateEmp() {
		Emp13 emp = new Emp13();
		System.out.println("사번> ");
		emp.setEmployeeId(sc.nextInt());
		System.out.println("연봉> ");
		emp.setSalary(sc.nextInt());
		
		dao.update(emp);
	}
	
	public void deleteEmp() {
		System.out.println("사번> ");
		int employeeId = sc.nextInt();
		
		dao.delete(employeeId);
	}
	
	public void selectOne() {
		System.out.println("사번> ");
		int employeeId = sc.nextInt();
		
		System.out.println(dao.selectOne(employeeId));
	}
	
	public void selectAll() {
		List<Emp13> list = new ArrayList<>();
		list = dao.selectAll();
		
		for (Emp13 emp13 : list) {
			System.out.println(emp13);
		}
	}
}
