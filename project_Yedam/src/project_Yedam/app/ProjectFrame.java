package project_Yedam.app;

import project_Yedam.VO.Article;
import project_Yedam.VO.BoardType;
import project_Yedam.VO.MailboxType;
import project_Yedam.VO.User;

public class ProjectFrame {

	public void run() {

		LogInPage logInPage = LogInPage.getInstance();

		while (true) {
			
			int menu = logInPage.showMenu(); // 1.sign in 2.sign up 9.exit

			User loggedInUser = null;

			if (menu == 1) {
				// sign in
				loggedInUser = logInPage.signIn();

				if (loggedInUser == null) {
					System.out.println("로그인 중 오류");
					continue;
				}
				
				logInPage.printWelcome(loggedInUser);

			} else if (menu == 2) {
				// sign up
				logInPage.signUp();
				continue;

			} else if (menu == 9) {
				// terminate
				System.out.println("프로그램을 종료합니다.");
				return;
			}
//			===============================================================================
			while (true) {
				
				menu = logInPage.afterLogin();	// 1.자유게시판 2.익명게시판 3.쪽지함 9.로그아웃

				if (menu == 1) {
					printBoard(loggedInUser, BoardType.FREE);
					
				} else if (menu == 2) {
					printBoard(loggedInUser, BoardType.ANONYMOUS);

				} else if (menu == 3) {
					printLetterMenu(loggedInUser);

				} else if (menu == 9) {
					System.out.println("초기화면으로 돌아갑니다.\n");
					break;
				}

			}
		}
	} // end of program
	
	
	
	
	
	void printBoard(User loggedInUser, BoardType type) {
		
		BoardPage boardPage = BoardPage.getInstance();
		String boardType = boardPage.checkBoardType(type);
		
		while (true) {
			
			int menu = 0;
			// show all list
			boardPage.showArticleList(boardType);
			
			//todo : page separation
			
			menu = boardPage.printMenu();	// 1.읽기 2.새글작성 3.수정 4.삭제 9.이전메뉴
			
			if (menu == 1) {
				
				Article article = boardPage.selectArticle(boardType);
				boardPage.printArticle(loggedInUser, article, boardType);
				
				// todo : comment
				
			} else if (menu == 2) {
				// new article
				boardPage.newArticle(boardType, loggedInUser);
				
			} else if (menu == 3) {
				// update article
				boardPage.updateArticle(boardType, loggedInUser);
				
			} else if (menu == 4) {
				// delete article
				boardPage.deleteArticle(boardType, loggedInUser);
				
			} else if (menu == 9) {
				// return to previous menu
				System.out.println("이전 메뉴로 이동합니다.\n");
				break;
			}
		}
	}

	
	void printLetterMenu(User loggedInUser) {
		
		LetterPage letterPage = LetterPage.getInstance();
		
		while (true) {
			// 1.사용자검색 2.쪽지보내기 3.받은쪽지함 4.보낸쪽지함 9.이전메뉴 
			int menu = letterPage.printMenu();

			if (menu == 1) {
				// search user
				letterPage.searchUser();
				
			} else if (menu == 2) {
				// send letter
				letterPage.sendLetter(loggedInUser);

			} else if (menu == 3) {
				String mailboxType = letterPage.checkMailboxType(MailboxType.RECEIVED);
				printMailbox(loggedInUser, letterPage, mailboxType);

			} else if (menu == 4) {
				String mailboxType = letterPage.checkMailboxType(MailboxType.SENT);
				printMailbox(loggedInUser, letterPage, mailboxType);
				
			} else if (menu == 9) {
				System.out.println("이전 메뉴로 이동합니다.");
				break;
			}
		}
	}
	
	void printMailbox(User loggedInUser, LetterPage letterPage, String mailboxType) {

		while (true) {
			// 1.전체쪽지 2.이름검색 3.내용검색 9.이전메뉴
			int menu = letterPage.printLetterBoxMenu();

			if ((menu == 1) || (menu == 2) || (menu == 3)) {
				letterPage.printLetters(loggedInUser, menu, mailboxType);
			
			} else if (menu == 9) {
				System.out.println("이전 메뉴로 이동합니다.");
				break;
			}
		}
	}
	
	
	
	
}
