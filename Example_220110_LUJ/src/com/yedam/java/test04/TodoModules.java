package com.yedam.java.test04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoModules {
	
	// 이용할 메소드들과 할 일 목록은 하나만 필요하므로 싱글톤으로 생성
	private static TodoModules todoModules = new TodoModules();

	// TodoInfo(할 일 객체)를 원소로 하는 arraylist
	private List<TodoInfo> list = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	
	
	private TodoModules() {}
	
	
	static TodoModules getInstance() {
		return todoModules;
	}
	
	// 메뉴 출력
	public int showMenu() {
		System.out.println("=== 1.할일입력 | 2.전체조회 | 3.할일조회 | 4.할일완료 | 5.종료 ===");
		System.out.print("선택>");
		int num = Integer.parseInt(sc.nextLine());
		
		return num;
	}
	
	// arraylist에 TodoInfo add
	public void writeTodo() {
		System.out.print("할 일> ");
		String input = sc.nextLine();
		TodoInfo todo = new TodoInfo(input);
		list.add(todo);
	}
	
	// arraylist의 모든 원소 출력
	public void showAllTodos() {
		for (TodoInfo todo : list) {
			String todoDone = todo.getIsDone() ? "완료" : "미완료";
			System.out.printf("%d번 %s %s\n",todo.getTodoNo(), todo.getContent(), todoDone);
		}
	}
	
	// arraylist의 원소들 중 미완료인 객체만 출력
	public void searchTodo() {
		for (TodoInfo todo: list) {
			if (todo.getIsDone() == false) {
				String todoDone = todo.getIsDone() ? "완료" : "미완료";
				System.out.printf("%d번 %s %s\n",todo.getTodoNo(), todo.getContent(), todoDone);
			}
		}
	}
	
	// 선택한 객체를 완료 상태로 전환
	public void finishTodo() {
		System.out.print("완료할 할 일을 선택해주세요> ");
		int todoNo = Integer.parseInt(sc.nextLine());
		for (TodoInfo todo: list) {
			if(todoNo == todo.getTodoNo() && !todo.getIsDone()) {
				todo.setIsDone(true);
				System.out.println("해당 할 일을 완료처리하였습니다.");
				break;
			} else if (todoNo == todo.getTodoNo()){
				System.out.println("해당 할 일은 이미 완료되었습니다.");
				continue;
			}
		}		
	}
}
