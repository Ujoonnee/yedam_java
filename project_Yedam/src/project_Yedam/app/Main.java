package project_Yedam.app;

import java.sql.Timestamp;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(System.currentTimeMillis());
		System.out.println(new Timestamp(System.currentTimeMillis()));
		new ProjectFrame().run();

	}

}
