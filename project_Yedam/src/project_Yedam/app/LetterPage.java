package project_Yedam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_Yedam.VO.Letter;
import project_Yedam.VO.MailboxType;
import project_Yedam.VO.User;
import project_Yedam.dao.LetterDAOImpl;
import project_Yedam.dao.ProjectDAO;
import project_Yedam.dao.UserDAOImpl;

public class LetterPage {

	private static LetterPage instance = new LetterPage();
	private LetterPage() {}
	public static LetterPage getInstance(User loggedInUser) {
		LetterPage.loggedInUser = loggedInUser;
		return instance;
	}
	
	
	private Scanner sc = new Scanner(System.in);
	private ProjectDAO<Letter, String> letterDao = LetterDAOImpl.getInstance();
	private static User loggedInUser = null;
	private String mailboxType = null;
	private Main console = new Main();
	
	public String getMailboxType() {
		return mailboxType;
	}
	
	public void setMailboxType(MailboxType mailboxType) {
		switch (mailboxType) {
		case RECEIVED:
			this.mailboxType = "받은쪽지함";
			break;
		case SENT:
			this.mailboxType = "보낸쪽지함";
			break;
		}
	}
	
	
	
	
	
	public void printLetterMenu(User loggedInUser) {
		
		while (true) {
			// 1.쪽지보내기 2.받은쪽지함 3.보낸쪽지함 4.사용자검색 9.이전메뉴 
			int menu = printMenu();

			if (menu == 1) {
				// send letter
				sendLetter();

			} else if (menu == 2) {
				setMailboxType(MailboxType.RECEIVED);
				printMailbox(loggedInUser);

			} else if (menu == 3) {
				setMailboxType(MailboxType.SENT);
				printMailbox(loggedInUser);
				
			} else if (menu == 4) {
				// search user
				searchUser();
				
			} else if (menu == 9) {
				console.clear();
				System.out.println("이전 메뉴로 이동합니다.\n");
				break;
			}
		}
	}
	
	
	public void printMailbox(User loggedInUser) {

		while (true) {
			// 1.전체쪽지 2.이름검색 3.내용검색 9.이전메뉴
			int menu = printLetterBoxMenu();

			if ((menu == 1) || (menu == 2) || (menu == 3)) {
				printLetters(menu);
			
			} else if (menu == 9) {
				console.clear();
				System.out.println("이전 메뉴로 이동합니다.\n");
				break;
			}
		}
	}
	
	
	
	public int printMenu() {
		
		int menu = 0;
		System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("\n    1.쪽지보내기     2.받은쪽지함     3.보낸쪽지함     4.사용자검색     9.이전메뉴    \n");
		System.out.println("└───────────────────────────────────────────────────────────────────────┘");
		System.out.print("\n>> ");
		
		menu = Integer.parseInt(sc.nextLine());
		
		console.clear();
		
		return menu;
	}
	
	// 1.쪽지보내기 ================================================================================
	public void sendLetter() {
		
		console.clear();
		
		Letter letter = new Letter();
		letter.setSenderId(loggedInUser.getId());

		// setRecipientId()
		System.out.println("┌─────────────────────────────── 쪽지보내기 ───────────────────────────────┐\n");
		System.out.print("수신자 ID를 입력하세요.\n\n>>");
		String recipientId = sc.nextLine();
		letter.setRecipientId(recipientId);
		
		// setContent()
		if (newContent(letter) == null) {
			return;	// canceled sendLetter in newContent()
		}
		
		letterDao.insert(letter);
		System.out.println("쪽지를 보냈습니다.\n");
		
	}
	
	public void sendLetter(Letter letter) {
			
			Letter reply = new Letter();
			reply.setSenderId(loggedInUser.getId());
	
			// setRecipientId()
			reply.setRecipientId(letter.getSenderId());
			
			// setContent()
			if (newContent(reply) == null) {
				return;	// canceled sendLetter in newContent()
			}
			
			letterDao.insert(reply);
			System.out.println("쪽지를 보냈습니다.\n");
			
		}
	
	Letter newContent(Letter letter) {
		Content : while(true) {
			// content
			System.out.println("\n쪽지 내용을 입력하세요.");
			System.out.println("작성을 종료하려면 \"작성종료\"를 입력하세요.");
			System.out.println("이전 줄을 지우려면 \"행삭제\"를 입력하세요.");
						
			String content = "";
			List<String> lines = new ArrayList<>();
						
			ContentBuilder : while(true) {
				System.out.print(">> ");
				String input = sc.nextLine();
				
				if (input.equals("작성종료") || input.equals("행삭제")) {
					if (lines.size() == 0) {
						System.out.println("\n작성을 종료합니다.");
//						letter.setContent(content);
						return null;
						
					} else if (input.equals("작성종료")) {
						// confirmation
						while(true) {
							int menu;
							try {
								System.out.println("\n     1.확인       2.계속 작성하기       3.새로 작성하기       9.쪽지보내기 취소     ");
								System.out.println("└───────────────────────────────────────────────────────────────────────┘");
								System.out.print("\n> ");
								menu = Integer.parseInt(sc.nextLine());
									
							} catch (Exception e) {
								System.err.println("\n잘못 입력하셨습니다.");
								continue;
							}
							if (menu == 1) {
								console.clear();
								System.out.println("\n작성을 완료합니다.");
								for (String string : lines) {
									content += string;
								}
								letter.setContent(content);
								return letter;
								
							} else if (menu == 2) {
								console.clear();
								System.out.println("\n이어서 작성합니다.\n");
								System.out.println("┌─────────────────────────────── 쪽지보내기 ───────────────────────────────┐\n");
								for (String string : lines) {
									System.out.print(string);
								}
								continue ContentBuilder;
										
							} else if (menu == 3) {
								console.clear();
								System.out.println("\n새로 작성합니다.\n");
								System.out.println("┌─────────────────────────────── 쪽지보내기 ───────────────────────────────┐\n");
								continue Content;
											
							} else if (menu == 9) {
								System.out.println("\n이전 메뉴로 돌아갑니다.");
								return null;
							}
							System.err.println("\n없는 메뉴입니다.");
						}
									
					} else if (input.equals("행삭제")) {
						lines.remove(lines.size() - 1);
						System.out.println("\n이전 행 삭제 완료\n");
						for (String string : lines) {
							System.out.println(string);
						}
					}
								
				} else {
					lines.add(input + "\n");
				}
			}
		}
		
	}

	// 2.받은쪽지함 ================================================================================
	
	public int printLetterBoxMenu() {
		
		while(true) {
			
			System.out.println("┌─────────────────────────────  " + mailboxType + "  ───────────────────────────────┐\n");
			System.out.println("       1.전체쪽지         2.이름검색         3.내용검색       ㅤ 9.이전메뉴       ");
			System.out.println("\n└───────────────────────────────────────────────────────────────────────┘");
			System.out.print("\n>> ");

			int menu = 0;
			try {
				menu = Integer.parseInt(sc.nextLine());
				
			} catch (Exception e) {
				console.clear();
				System.out.println("잘못 선택하셨습니다.\n");
				continue;
			}
			
			return menu;
		}
	}
	
	public void printLetters(int menu) {
		
		List<Letter> letterList = letterDao.selectAll();
		List<Letter> letters = null;
		
		if (mailboxType.equals("받은쪽지함")) {
			
			switch(menu) {
			case 1:
				letters = printAllReceivedLetters(letterList);
				break;
			case 2:
				letters = searchSender(letterList);
				break;
			case 3:
				letters = searchRecievedContent(letterList);
			}
			
		} else if (mailboxType.equals("보낸쪽지함")) {
			
			switch(menu) {
			case 1:
				letters = printAllSentLetters(letterList);
				break;
			case 2:
				letters = searchRecipient(letterList);
				break;
			case 3:
				letters = searchSentContent(letterList);
			}
			
		}
		
		if (letters.size() == 0 || letters == null) {
			console.clear();
			System.out.println("쪽지가 없습니다.\n");
		} else {
			readLetter(letters);
		}
	}
	
//	List<Letter> checkListNull(List<Letter> letters) {
//		if (letters.size() == 0) {
//			System.out.println("쪽지가 없습니다.");
//			return null;
//		} else {
//			return letters;
//		}
//	}
	
	List<Letter> printAllReceivedLetters(List<Letter> letterList) {
		
		List<Letter> allLetters = new ArrayList<>();
		
		console.clear();
		
		System.out.println("\n\n\n┌──────────────────────────────  전체쪽지  ────────────────────────────────┐\n");
		System.out.printf("%-3s %4s      %4s\t%-3s   %10s%-10s\n", "번호", "날짜", "보낸사람", "상태", "내", "용");
		
		int listNum = 1;
		for (Letter letter : letterList) {
			if (letter.getRecipientId().equals(loggedInUser.getId())) {
				letter.setLetterNum(listNum);
				allLetters.add(letter);
				listNum++;
				if (letter.getContent() == null) {
					letter.setContent("쪽지 내용 작성 중 오류가 발생했습니다.");
				}
				
				letter.printList(mailboxType);
			}
		}
		System.out.println();
		
		return allLetters;
	}
	
	List<Letter> searchSender(List<Letter> letterList) {
		
		List<Letter> letters = new ArrayList<>();
		
		System.out.print("이름을 입력하세요.\n>> ");
		String name = sc.nextLine();
		System.out.println("\n┌──────────────────────────────  이름검색  ────────────────────────────────┐\n");
		System.out.printf("%-3s %4s      %4s\t%-3s   %10s%-10s\n", "번호", "날짜", "보낸사람", "상태", "내", "용");

		
		int listNum = 1;
		for (Letter l : letterList) {
			if (l.getSenderName().equals(name) && l.getRecipientId().equals(loggedInUser.getId())) {
				l.setLetterNum(listNum);
				letters.add(l);
				listNum++;
				
				l.printList(mailboxType);
			}
		}
		System.out.println();
		
		return letters;
	}
	
	List<Letter> searchRecievedContent(List<Letter> letterList) {
		
		List<Letter> letters = new ArrayList<>();
		
		System.out.print("내용을 입력하세요.\n> ");
		String content = sc.nextLine();
		
		int listNum = 1;
		for (Letter l : letterList) {
			if (l.getContent().contains(content) && l.getRecipientId().equals(loggedInUser.getId())) {
				l.setLetterNum(listNum);
				letters.add(l);
				listNum++;
				
				l.printList(mailboxType);
			}
		}
		System.out.println();
		
		return letters;
	}
	
	void readLetter(List<Letter> letters) {
		
		int menu = 0;
		
		while (true) {
//			System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
			System.out.println("                   1.쪽지읽기                   9.이전메뉴                   ");
			System.out.println("└───────────────────────────────────────────────────────────────────────┘");
			
			try {
				System.out.print("\n\n>> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			if (menu == 1) {
				while (true) {
					try {
						System.out.print("쪽지 번호 선택\n> ");
						menu = Integer.parseInt(sc.nextLine());
						Letter letter = null;
						for (Letter l : letters) {
							if (l.getLetterNum() == menu) {
								letterDao.update(l);
								letter = l;
							}
						}
						
						if (letter == null) {
							System.err.println("잘못 입력하셨습니다.");
						}
						
						System.out.println("\n\n\n─────────────────────────────────────────────────────────────────────────");
						System.out.println(letter);
						followUpMenu(letter);
						break;
						
					} catch (Exception e) {
						System.err.println("\n잘못 입력하셨습니다.\n");
						continue;
					}
				}
				
			} else if(menu == 9) {
				console.clear();
				System.out.println("이전 메뉴료 이동합니다.\n");
				return ;
				
			}
		}
	}

	void followUpMenu(Letter letter) {
		
		while (true) {
//			System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
			System.out.println("            1.답장하기            2.삭제하기            9.이전메뉴             ");
			System.out.println("└───────────────────────────────────────────────────────────────────────┘");
			
			int menu = 0;
			try {
				System.out.print("메뉴 선택\n> ");
				menu = Integer.parseInt(sc.nextLine());
				
			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}
			
			if (menu == 1) {	// reply
				sendLetter(letter);
				return;

			} else if(menu == 2) {
				System.out.println("삭제 후에는 복구할 수 없습니다. 삭제하시겠습니까?");
				
				while (true) {
					System.out.print("1.삭제 9.이전메뉴\n> ");
					int select = 0;

					try {
						select = Integer.parseInt(sc.nextLine());

					} catch (Exception e) {
						System.err.println("\n잘못 입력하셨습니다.\n");
						continue;
					}

					if (select == 1) {
						letterDao.delete(letter);
						System.out.println("\n삭제되었습니다.");
						return;

					} else if (select == 9) {
						System.out.println("\n이전 메뉴로 돌아갑니다.");
						return;

					} else {
						System.err.println("\n없는 메뉴입니다.\n");
					}
				
				}
				
			} else if (menu == 9) {
				System.out.println("\n이전 메뉴로 돌아갑니다.");
				break;
			}
		}
		
	}
	
// 3.보낸쪽지함 ================================================================================
	
	List<Letter> printAllSentLetters(List<Letter> letterList) {
		
		List<Letter> allLetters = new ArrayList<>();
		
		System.out.println("\n\n\n─────────────────────────────────────────────────────────────────────────\n");
		System.out.printf("%-3s %4s      %4s\t%-3s   %10s%-10s\n", "번호", "날짜", "받는사람", "상태", "내", "용");
		
		int listNum = 1;
		for (Letter letter : letterList) {
			if (letter.getSenderId().equals(loggedInUser.getId())) {
				letter.setLetterNum(listNum);
				allLetters.add(letter);
				listNum++;
				
				letter.printList(mailboxType);
			}
		}
		System.out.println();
		
		return allLetters;
	}
	
	List<Letter> searchRecipient(List<Letter> letterList) {
		
		List<Letter> letters = new ArrayList<>();
		
		System.out.print("이름을 입력하세요.\n> ");
		String name = sc.nextLine();
		
		int listNum = 1;
		for (Letter l : letterList) {
			if (l.getRecipientName().equals(name) && l.getSenderId().equals(loggedInUser.getId())) {
				l.setLetterNum(listNum);
				letters.add(l);
				listNum++;
				
				l.printList(mailboxType);
			}
		}
		System.out.println();
		
		return letters;
		
	}
	
	List<Letter> searchSentContent(List<Letter> letterList) {
		
		List<Letter> letters = new ArrayList<>();
		
		System.out.print("내용을 입력하세요.\n> ");
		String content = sc.nextLine();
		
		int listNum = 1;
		for (Letter l : letterList) {
			if (l.getContent().contains(content) && l.getRecipientId().equals(loggedInUser.getId())) {
				l.setLetterNum(listNum);
				letters.add(l);
				listNum++;
				
				l.printList(mailboxType);
			}
		}
		System.out.println();
		
		return letters;
	}
	

	// 4.사용자검색 ================================================================================
	public void searchUser() {
		
		ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
		List<User> userList = userDao.selectAll();
		
		System.out.print("\n검색할 이름\n> ");
		String name = sc.nextLine();
		
		int count = 0;
		for (User user : userList) {
			if (user.getName().equals(name)) {
				System.out.println(user);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("검색결과가 없습니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
