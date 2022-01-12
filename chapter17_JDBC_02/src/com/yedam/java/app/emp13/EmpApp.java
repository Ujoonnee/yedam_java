package com.yedam.java.app.emp13;

import java.util.List;
import java.util.Scanner;

public class EmpApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Emp> list = null; // ArrayList<Emp>
		EmpDAO dao = EmpDAO.getInstance();
		Emp emp = null;
		boolean run = true;

		while (run) {
			System.out.println();
			System.out.println("| 1.등록 | 2.수정 | 3.삭제 | 4.전체조회 | 5.단건조회 | 9.종료 |");
			
			System.out.println("메뉴> ");
			int menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				emp = new Emp();
				System.out.println("사번> ");
				emp.setEmployeeId(sc.nextInt());
				System.out.println("이름> ");
				emp.setFirstName(sc.next());
				System.out.println("직무> ");
				emp.setJobId(sc.next());
				System.out.println("연봉> ");
				emp.setSalary(sc.nextInt());
				System.out.println("상여율> ");
				emp.setCommissionPct(sc.next());
				System.out.println("부서명> ");
				emp.setDepartmentName(sc.next());
				System.out.println("지역번호> ");
				emp.setLocationId(sc.nextInt());
				
				dao.insert(emp);
				break;
			case 2:
				emp = new Emp();
				System.out.println("사번> ");
				emp.setEmployeeId(sc.nextInt());
				System.out.println("연봉> ");
				emp.setSalary(sc.nextInt());
				
				dao.update(emp);
				break;
			case 3:
				System.out.println("사번> ");
				dao.delete(sc.nextInt());
				break;
			case 4:
				list = dao.selectAll();
				for (Emp emp13 : list) {
					System.out.println(emp13);
				}
				break;
			case 5:
				System.out.println("사번> ");
				System.out.println(dao.selectOne(sc.nextInt()));
				break;
			case 9:
				run = false;
				System.out.println("종료");
				break;
			}
		}
	}

}
