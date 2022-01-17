package project_Yedam.app;

import java.util.List;
import java.util.Scanner;

import project_Yedam.VO.Letter;
import project_Yedam.VO.User;
import project_Yedam.dao.LetterDAOImpl;
import project_Yedam.dao.ProjectDAO;
import project_Yedam.dao.UserDAOImpl;

public class LogInPage {

	private static LogInPage instance = new LogInPage();

	private LogInPage() {
	}

	public static LogInPage getInstance() {
		return instance;
	}

	
	private Scanner sc = new Scanner(System.in);
	private ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
	private Main console = new Main();

	
	
	public int showMenu() {

		System.out.println("\n┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\n        1.로그인                 2.회원가입                   9.종료         \n");
		System.out.println("└───────────────────────────────────────────────────────────────────────┘");
		while (true) {
			int menu = 0;
			try {
				System.out.print("\n>> ");
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

		console.clear();

		// id
		int logInTry = 0;
		Id: while (logInTry < 3) {
			System.out.print("\nID를 입력하세요.\n\n>> ");
			userId = sc.nextLine();

			for (User user : userList) {
				if (user.getId().equals(userId)) {
					break Id;
				}
			}

			logInTry++;
			System.err.println("\nID가 존재하지 않습니다. " + logInTry + "/3");

			if (logInTry == 3) {
				console.clear();
				System.out.println("초기화면으로 돌아갑니다.\n");
				return logInUser;
			}
		}

		// password
		logInTry = 0;
		while (logInTry < 3) {
			System.out.print("\n비밀번호를 입력하세요.\n\n>> ");
			userPw = sc.nextLine();

			for (User user : userList) {
				if (user.getId().equals(userId) && user.getPassword().equals(userPw)) {
					logInUser = user;
					System.out.println("로그인 성공\n");
					return logInUser;
				}
			}

			logInTry++;
			System.err.println("\n비밀번호가 일치하지 않습니다." + logInTry + "/3");
			if (logInTry == 3) {
				System.out.println("초기화면으로 돌아갑니다.");
				return logInUser;
			}
		}

		System.err.println("로그인 오류");
		return null;
	}

	public void printWelcome(User loggedInUser) {
		System.out.printf("%33s%-1s", "안녕하세요, ", loggedInUser.getName() + "님 :)\n");

		ProjectDAO<Letter, String> letterDao = LetterDAOImpl.getInstance();
		List<Letter> letters = letterDao.selectAll();
		int count = 0;
		for (Letter l : letters) {
			if (l.getRecipientId().equals(loggedInUser.getId()) && (l.isRead() == 0)) {
				count++;
			}
		}
		System.out.printf("%43s", "새로운 쪽지가 " + count + "건 있습니다.\n\n");
	}

	public void signUp() {

		User newUser = new User();

		while (true) {

			console.clear();

			String name = newName();
			newUser.setName(name);

			String id = newId();
			newUser.setId(id);

			String password = newPassword();
			newUser.setPassword(password);

			String phoneNum = newPhoneNum();
			newUser.setPhoneNum(phoneNum);

			while (true) {
				System.out.printf("  이름 : %s\n  ID : %s\n  비밀번호 : %s\n  전화번호 : %s\n\n", name, id, password, phoneNum);
				System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
				System.out.println("\n            1.가입하기            2.수정하기            9.회원가입 취소          \n");
				System.out.println("└───────────────────────────────────────────────────────────────────────┘");
				System.out.print("\n>> ");

				int input = Integer.parseInt(sc.nextLine());

				if (input == 1) {
					console.clear();

					userDao.insert(newUser);

					System.out.printf("\n%35s%-10s", "환영합니다, ", name + "님!\n");
					return;

				} else if (input == 2) {
					
					while (true) {
						console.clear();
						
						System.out.printf("  이름 : %s\n  ID : %s\n  비밀번호 : %s\n  전화번호 : %s\n\n", name, id, password, phoneNum);
						System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
						System.out.println("\n      1.이름        2.ID        3.비밀번호        4.전화번호       9.이전메뉴    \n");
						System.out.println("└───────────────────────────────────────────────────────────────────────┘");
						System.out.print("\n>> ");

						input = Integer.parseInt(sc.nextLine());

						if (input == 1) {
							name = newName();
							newUser.setName(name);
							break;
							
						} else if (input == 2) {
							id = newId();
							newUser.setId(id);
							break;
							
						} else if (input == 3) {
							password = newPassword();
							newUser.setPassword(password);
							break;
							
						} else if (input == 4) {
							phoneNum = newPhoneNum();
							newUser.setPhoneNum(phoneNum);
							break;
							
						} else if (input == 9) {
							console.clear();
							break;
							
						} else {
							console.clear();
							continue;
						}
					}

				} else if (input == 9) {
					console.clear();
					System.out.println("초기화면으로 돌아갑니다.");
					return;
				}
			}

		}

	}

	public String newName() {

		String name = null;
		while (true) {
			System.out.print("\n이름을 입력하세요.\n\n>> ");
			try {
				name = sc.nextLine();

				if (name.equals("")) {
					continue;

				} else if (name.contains(" ")) {
					System.out.println("\n이름에는 공백이 포함될 수 없습니다.");
					continue;
				}

				Integer.parseInt(name);
				System.err.println("이름은 숫자로만 이루어질 수 없습니다.");
				continue;

			} catch (NumberFormatException e) {
				console.clear();
				return name;
			}
		}
	}

	public String newId() {

		String id = null;
		Id: while (true) {

			System.out.print("\nID를 입력하세요.\n\n>> ");
			try {
				id = sc.nextLine();

				if (id.length() < 4) {
					System.err.println("\nID는 4글자 이상이어야 합니다.");
					continue;

				} else if (id.contains(" ")) {
					System.out.println("\nID는 공백이 포함될 수 없습니다.");
					continue;

				} else {
					List<User> userList = userDao.selectAll();
					for (User user : userList) {
						if (user.getId().equals(id)) {
							System.err.println("\n이미 사용중인 ID입니다.");
							continue Id;
						}
					}
				}

				Integer.parseInt(id);
				System.err.println("\nID는 숫자로만 이루어질 수 없습니다.");
				continue;

			} catch (NumberFormatException e) {
				console.clear();
				return id;
			}
		}
	}

	public String newPassword() {

		String password = null;
		while (true) {

			System.out.print("\n비밀번호를 입력하세요.\n\n>> ");
			password = sc.nextLine();

			if (password.length() < 4) {
				System.err.println("\n비밀번호는 4글자 이상이어야 합니다.");
				continue;

			} else if (password.contains(" ")) {
				System.out.println("\n비밀번호는 공백이 포함될 수 없습니다.");
				continue;
			}

			System.out.print("\n비밀번호 확인\n>> ");
			String pw2 = sc.nextLine();

			if (password.equals(pw2)) {
				console.clear();
				return password;

			} else {
				System.err.println("\n비밀번호가 일치하지 않습니다.");
			}
		}
	}

	public String newPhoneNum() {

		String phoneNum = null;
		while (true) {
			System.out.print("\n전화번호를 입력하세요. ( \"-\" 제외 )\n\n>> ");
			phoneNum = sc.nextLine().replace("-", "").replace(" ", "");

			try {
				Integer.parseInt(phoneNum);
			} catch (Exception e) {
				System.err.println("\n다시 입력하세요.");
				continue;
			}

			console.clear();
			return phoneNum;
		}
	}

	public int afterLogin() {

		System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\n       1.자유게시판         2.익명게시판         3.쪽지함         9.로그아웃      \n");
		System.out.println("└───────────────────────────────────────────────────────────────────────┘");

		int menu;
		while (true) {
			try {
				System.out.print("\n>> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			if ((menu == 1) || (menu == 2) || (menu == 3) || (menu == 9)) {
				console.clear();
				return menu;
			}

			System.err.println("\n없는 메뉴입니다.\n");
		}
	}
}
