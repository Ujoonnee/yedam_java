package project_Yedam.app;

import project_Yedam.VO.BoardType;
import project_Yedam.VO.User;

public class ProjectFrame {

	private Main console = new Main();
	
	public void run() {

		LogInPage logInPage = LogInPage.getInstance();

		while (true) {
			
			int menu = logInPage.showMenu(); // 1.sign in 2.sign up 9.exit

			User loggedInUser = null;

			if (menu == 1) {
				// sign in
				loggedInUser = logInPage.signIn();

				if (loggedInUser == null) {
					continue;
				}
				
				console.clear();
				
				logInPage.printWelcome(loggedInUser);

			} else if (menu == 2) {
				// sign up
				logInPage.signUp();
				continue;

			} else if (menu == 9) {
				console.clear();
				System.out.println("프로그램을 종료합니다.");
				return;
			}
//			===============================================================================
			BoardPage boardPage = BoardPage.getInstance(loggedInUser);
			LetterPage letterPage = LetterPage.getInstance(loggedInUser);
			
			while (true) {
				
				menu = logInPage.afterLogin();	// 1.자유게시판 2.익명게시판 3.쪽지함 9.로그아웃

				if (menu == 1) {
					boardPage.printBoard(loggedInUser, BoardType.FREE);
					
				} else if (menu == 2) {
					boardPage.printBoard(loggedInUser, BoardType.ANONYMOUS);

				} else if (menu == 3) {
					letterPage.printLetterMenu(loggedInUser);

				} else if (menu == 9) {
					console.clear();
					System.out.println("초기화면으로 돌아갑니다.\n");
					break;
				}

			}
		}
	} // end of program
	
	
//	
//	void printLetterMenu(User loggedInUser) {
//		
//		LetterPage letterPage = LetterPage.getInstance(loggedInUser);
//		
//		while (true) {
//			// 1.쪽지보내기 2.받은쪽지함 3.보낸쪽지함 4.사용자검색 9.이전메뉴 
//			int menu = letterPage.printMenu();
//
//			if (menu == 1) {
//				// send letter
//				letterPage.sendLetter();
//
//			} else if (menu == 2) {
//				String mailboxType = letterPage.checkMailboxType(MailboxType.RECEIVED);
//				printMailbox(loggedInUser, letterPage, mailboxType);
//
//			} else if (menu == 3) {
//				String mailboxType = letterPage.checkMailboxType(MailboxType.SENT);
//				printMailbox(loggedInUser, letterPage, mailboxType);
//				
//			} else if (menu == 4) {
//				// search user
//				letterPage.searchUser();
//				
//			} else if (menu == 9) {
//				System.out.println("이전 메뉴로 이동합니다.");
//				break;
//			}
//		}
//	}
//	
//	
//	void printMailbox(User loggedInUser, LetterPage letterPage, String mailboxType) {
//
//		while (true) {
//			// 1.전체쪽지 2.이름검색 3.내용검색 9.이전메뉴
//			int menu = letterPage.printLetterBoxMenu(mailboxType);
//
//			if ((menu == 1) || (menu == 2) || (menu == 3)) {
//				letterPage.printLetters(menu, mailboxType);
//			
//			} else if (menu == 9) {
//				System.out.println("이전 메뉴로 이동합니다.");
//				break;
//			}
//		}
//	}
	
	
	
	
}
