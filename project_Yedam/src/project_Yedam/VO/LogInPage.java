package project_Yedam.VO;

import java.util.List;
import java.util.Scanner;

public class LogInPage {
	
	private static LogInPage instance = new LogInPage();
	private LogInPage() {}
	public static LogInPage getInstance() {
		return instance;
	}
	
	private Scanner sc = new Scanner(System.in);
	private ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();

	public int showMenu() {

		System.out.println("┌───────────┬────────────┬────────────┐");
		System.out.println("│  1.로그인	   2.회원가입	 	　9.종료　　 │");
		System.out.println("└───────────┴────────────┴────────────┘");
		while (true) {
			int menu = 0;
			try {
				System.out.print("메뉴\n> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}
			if ((menu == 1) || (menu == 2) || (menu == 9)) {
				return menu;
			}

			System.err.println("\n잘못 입력하셨습니다.\n");
		}
	}

	
	public User signIn() {

		List<User> userList = userDao.selectAll();
		User logInUser = null;
		String userId = null;
		String userPw = null;

		int logInTry = 0;
		Id: while (logInTry < 3) {
			System.out.print("\nID를 입력하세요.\n> ");
			userId = sc.nextLine();

			for (User user : userList) {
				if (user.getId().equals(userId)) {
					break Id;
				}
			}
			System.err.println("\nID가 존재하지 않습니다.");

			logInTry++;
			if (logInTry == 3) {
				System.out.println("초기화면으로 돌아갑니다.");
				return logInUser;
			}
		}

		logInTry = 0;
		while (logInTry < 3) {
			System.out.print("\n비밀번호를 입력하세요.\n> ");
			userPw = sc.nextLine();

			for (User user : userList) {
				if (user.getId().equals(userId) && user.getPassword().equals(userPw)) {
					logInUser = user;
					System.out.println("\n로그인 성공");
					return logInUser;
				}
			}

			logInTry++;
			System.err.println("\n비밀번호가 일치하지 않습니다." + logInTry + "회 틀렸습니다.");
			if (logInTry == 3) {
				System.out.println("초기화면으로 돌아갑니다.");
				return logInUser;
			}
		}

		System.err.println("로그인 오류");
		return null;
	}

	
	public void printWelcome(User loggedInUser) {
		System.out.println("안녕하세요, " + loggedInUser.getName() + "님😆");
		
		ProjectDAO<Letter, String> letterDao = LetterDAOImpl.getInstance();
		List<Letter> letters = letterDao.selectAll();
		int count = 0;
		for (Letter l : letters) {
			if (l.getRecipientId().equals(loggedInUser.getId()) && (l.isRead() == 0)) {
				count++;
			}
		}
		System.out.println("새로운 쪽지가 " + count + "건 있습니다.\n");
	}

	
	public void signUp() {

		User newUser = new User();

		// id validation
		while (true) {
			System.out.print("\nID를 입력하세요.\n> ");
			String id = null;
			try {
				id = sc.next();
				Integer.parseInt(id);
				System.err.println("ID는 숫자로만 이루어질 수 없습니다.");
				continue;
				
			} catch (NumberFormatException e) {
				newUser.setId(id);
			}
			break;
		}

		// password validation
		while (true) {
			System.out.print("\n비밀번호를 입력하세요.\n> ");
			String pw1 = sc.next();
			if (pw1.length() < 4) {
				System.err.println("\n비밀번호는 4글자 이상이어야 합니다.");
				continue;
			}

			System.out.print("\n비밀번호 확인\n> ");
			String pw2 = sc.next();
			if (pw1.equals(pw2)) {
				newUser.setPassword(pw2);
				break;
			} else {
				System.err.println("\n비밀번호가 일치하지 않습니다.");
			}
		}

		while (true) {
			System.out.print("\n이름을 입력하세요.\n> ");
			String name = null;
			try {
				name = sc.next();
				Integer.parseInt(name);
				System.err.println("ID는 숫자로만 이루어질 수 없습니다.");
				continue;
			} catch (NumberFormatException e) {
				newUser.setName(name);
			}
			break;
		}

		// phone number validation
		while (true) {
			System.out.print("\n전화번호를 입력하세요( \"-\" 제외 ).\n> ");
			String phoneNum = sc.next();

			try {
				Integer.parseInt(phoneNum);
			} catch (Exception e) {
				System.err.println("\n다시 입력하세요.");
				continue;
			}

			newUser.setPhoneNum(phoneNum);

			userDao.insert(newUser);
			System.out.println("회원가입을 축하합니다.🎉🎉");
			break;
		}
	}

	public int afterLogin() {

		System.out.println("┌───────────┬────────────┬────────────┬────────────┐");
		System.out.println("│ 1.자유게시판	│ 2.익명게시판	 │	 3.쪽지함	  │　 9.로그아웃  │");
		System.out.println("└───────────┴────────────┴────────────┴────────────┘");

		int menu;
		while (true) {
			try {
				System.out.print("메뉴\n> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			if ((menu == 1) || (menu == 2) || (menu == 3) || (menu == 9)) {
				return menu;
			}

			System.err.println("\n잘못 입력하셨습니다.\n");
		}
	}
}
