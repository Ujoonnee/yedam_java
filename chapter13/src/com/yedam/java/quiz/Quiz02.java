package com.yedam.java.quiz;

public class Quiz02 {
	/*
	 문제1) DaoExample 클래스의 main() 메소드에서 dbWork() 메소드를 호출할 때 OracleDao와 MysqlDao 객체를 매개값으로 주고 호출하였다. 
	   		 dbWork() 메소드는 두 객체를 모두 매개값으로 받기 위해 DataAccessObject 타입의 매개 변수를 가지고 있다. 
	   		 실행 결과를 보고 DataAccessObject 인터페이스와 OracleDao, MySqlDao 구현 클래스를 각각 작성해보자. 
	   		 - 실행코드
	   		 public class DaoExample {
   			public static void dbWork(DataAccessObject dao) {
       			dao.select();
       			dao.insert();
       			dao.update();
       			dao.delete();
   		}
   			public static void main(String[] args) {
       			dbWork(new OracleDao());
       			dbWork(new MySqlDao());

   				}

			}

	   		 - 실행결과
	   		 Oracle DB에서 검색
	   		 Oracle DB에 삽입
	   		 Oracle DB를 수정
	   		 Oracle DB에서 삭제
	   		 MySql DB에서 검색
	   		 MySql DB에 삽입
	   		 Mysql DB를 수정
	   		 MySql DB에서 삭제
*/
}
