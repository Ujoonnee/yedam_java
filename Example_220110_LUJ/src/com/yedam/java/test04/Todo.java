package com.yedam.java.test04;

public class Todo {

	public static void main(String[] args) {
		
		TodoModules todo = TodoModules.getInstance();
		
		int menu = 0;
		
		while(menu != 5) {
			
			menu = todo.showMenu();
			
			switch(menu) {
			case 1:
				todo.writeTodo();
				break;
			case 2:
				todo.showAllTodos();
				break;
			case 3:
				todo.searchTodo();
				break;
			case 4:
				todo.finishTodo();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
		}
	}
}
