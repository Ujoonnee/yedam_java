package com.yedam.java.app.dept;

import java.util.List;
import java.util.Scanner;

public class DepartmentApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Department dept = null;
		int departmentId = 0;
		boolean run = true;

		while (run) {
			System.out.println();
			System.out.println("1.등록 2.수정 3.삭제 4.단건조회 5.전체조회 9.종료");
			System.out.println("선택> ");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				dept = new Department();
				System.out.println("부서번호> ");
				dept.setDepartmentId(sc.nextInt());
				System.out.println("부서이름> ");
				dept.setDepartmentName(sc.next());
				System.out.println("매니저번호> ");
				dept.setManagerId(sc.next());
				System.out.println("지역번호> ");
				dept.setLocationId(sc.nextInt());
				dao.insert(dept);
				break;
			case 2:
				System.out.println("부서번호> ");
				departmentId = sc.nextInt();
				dept = new Department();
				dept.setDepartmentId(departmentId);
				System.out.println("수정> ");
				String updated = sc.next();
				dept.setDepartmentName(updated);
				dao.update(dept);
				break;
			case 3:
				System.out.println("부서번호> ");
				departmentId = sc.nextInt();
				dao.delete(departmentId);
				break;
			case 4:
				System.out.println("부서번호> ");
				departmentId = sc.nextInt();
				dept = dao.selectOne(departmentId);
				System.out.println(dept);
				break;
			case 5:
				List<Department> list = dao.selectAll();
				for (Department department : list) {
					System.out.println(department);
				}
				break;
			case 9:
				run = false;
				System.out.println("종료");
				break;
			}
		}
		
		sc.close();
	}
}
