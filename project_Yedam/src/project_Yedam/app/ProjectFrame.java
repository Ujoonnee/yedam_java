package project_Yedam.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import project_Yedam.VO.AnonymousBoard;
import project_Yedam.VO.FreeBoard;
import project_Yedam.VO.Letter;
import project_Yedam.VO.User;
import project_Yedam.dao.AnonymousBoardDAOImpl;
import project_Yedam.dao.FreeBoardDAOImpl;
import project_Yedam.dao.LetterDAOImpl;
import project_Yedam.dao.ProjectDAO;
import project_Yedam.dao.UserDAOImpl;

public class ProjectFrame {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = null;
		// todo : (now - getPostDate() > 1day) ? dtf1.format(getPostDate()) : dtf2.~~~
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");

//		===============================================================================
		// User page
		ProjectDAO<User> userDao = UserDAOImpl.getInstance();
		List<User> userList = userDao.selectAll();

		// show menu
		System.out.println("┌───────────┬────────────┬────────────┐");
		System.out.println("│  1.로그인	│  2.회원가입	 │　 　9.종료　　 │");
		System.out.println("└───────────┴────────────┴────────────┘");
		System.out.println("1.로그인 2.회원가입 9.종료");
		int menu1 = Integer.parseInt(sc.nextLine());
		boolean isLoggedIn = false;
		while (true) {
			// sign in
			User loggedInUser = null;
			if (menu1 == 1) {
				System.out.println("ID > ");
				String userId = sc.next();

				// get userInfo by id
				// matches password ? isLoggedIn = true;
				for (User logIn : userList) {
					if (logIn.getName().equals(userId)) {
						System.out.println("Password > ");
						String userPassword = sc.next();
						while (true) { // todo : 5 chances to login
							if (logIn.getPassword().equals(userPassword)) {
								isLoggedIn = true;
								System.out.println("로그인 성공");
								for (User user : userList) {
									if (user.getName() == userId) {
										loggedInUser = user; // authenticated user is loggedInUser
									}
								}
								break;
							} else {
								System.out.println("비밀번호가 틀렸습니다.");
							}
						}
					}
				}
				// todo : welcome message and new letter notification

				// sign up
			} else if (menu1 == 2) {
				User user = new User();
				System.out.println("ID > ");
				String id = sc.next(); // prevent int for id
				user.setId(id);
				System.out.println("비밀번호 > "); // todo : password check
				user.setPassword(sc.next());
				System.out.println("이름 > ");
				String name = sc.next(); // prevent int for name
				user.setName(name);
				System.out.println("전화번호 > ");
				user.setPhoneNum(sc.next());

				// register - unimplemented
				userDao.insert(user);

				// terminate
			} else if (menu1 == 9) {
				System.out.println("프로그램 종료");
				return;
			}
//			===============================================================================

			/*
			 * AUTHENTICATION NOT IMPLEMENTED!
			 * 
			 * Administrator can reach to every menu without permission normal user should
			 * take authentication before functions
			 * 
			 */

			// menu page / after login
			if (isLoggedIn) {
				System.out.println("┌───────────┬────────────┬────────────┬────────────┐");
				System.out.println("│ 1.자유게시판	│ 2.익명게시판	 │ㅤ3.사용자검색ㅤ│　 9.로그아웃  │");
				System.out.println("└───────────┴────────────┴────────────┴────────────┘");
				System.out.println("1.자유게시판 2.익명게시판 3.사용자검색 9.로그아웃");
				int menu2 = Integer.parseInt(sc.nextLine());

//				===============================================================================
				// free board page
				if (menu2 == 1) {
					ProjectDAO<FreeBoard> fBoardDao = FreeBoardDAOImpl.getInstance();
					List<FreeBoard> fBoardList = fBoardDao.selectAll();
					// show menu
					while (true) {
						System.out.println("┌──────────┬──────────┬──────────┬──────────┬──────────┐");
						System.out.println("│　　1.읽기　　│ 2.새글작성　│　　3.수정　　│　　4.삭제　　│ 9.이전메뉴　│");
						System.out.println("└──────────┴──────────┴──────────┴──────────┴──────────┘");
						int menuF = Integer.parseInt(sc.nextLine());
						if (menuF == 1) {
							// show all list
							for (FreeBoard freeBoard : fBoardList) {
								System.out.println(freeBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							FreeBoard fBoard = null;
							// get one article by boardNum
							for (FreeBoard freeBoard : fBoardList) {
								if (freeBoard.getBoardNum() == select) {
									fBoard = freeBoard;
								}
							}
							// show content of selected
							System.out.println("글 번호\t제목");
							System.out.println(fBoard.getBoardNum() + "\t" + fBoard.getTitle());
							System.out.println("작성자 : " + fBoard.getPoster() + " " + fBoard.getPostDate());
							System.out.println("\n" + fBoard.getContent());

						} else if (menuF == 2) {
							// new article
							FreeBoard fBoard = new FreeBoard();
							fBoard.setPoster(loggedInUser.getName());
							System.out.println("제목 >");
							String title = sc.nextLine();
							fBoard.setTitle(title);
							System.out.println("글 내용 >");
							String content = sc.nextLine();
							fBoard.setContent(content);
//							fBoard.setPostDate();				todo : dtf must be preceded

							fBoardDao.insert(fBoard);

						} else if (menuF == 3) {
							// update article
							// todo : authority handling
							// show all list
							for (FreeBoard freeBoard : fBoardList) {
								System.out.println(freeBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							FreeBoard fBoard = null;
							// get one article by boardNum
							for (FreeBoard freeBoard : fBoardList) {
								if (freeBoard.getBoardNum() == select) {
									fBoard = freeBoard;
								}
							}
							// update that article
							System.out.println("제목 > ");
							String newTitle = sc.nextLine();
							fBoard.setTitle(newTitle);
							System.out.println("글 내용 > ");
							String newContent = sc.nextLine();
							fBoard.setContent(newContent);
//							fBoard.setPostDate();				todo : dtf must be preceded

							fBoardDao.update(fBoard);

						} else if (menuF == 4) {
							// delete article
							// todo : authority handling
							// show all list
							for (FreeBoard freeBoard : fBoardList) {
								System.out.println(freeBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							FreeBoard fBoard = null;
							// get one article by boardNum
							for (FreeBoard freeBoard : fBoardList) {
								if (freeBoard.getBoardNum() == select) {
									fBoard = freeBoard;
								}
							}
							// delete
							// todo : delete check
							fBoardDao.delete(fBoard);

						} else if (menuF == 9) {
							// return to previous menu
							System.out.println("이전 메뉴로 이동합니다.");
							break;
						}
					}

//				===============================================================================
					// anonymous board page
				} else if (menu2 == 2) {
					ProjectDAO<AnonymousBoard> aBoardDao = AnonymousBoardDAOImpl.getInstance();
					List<AnonymousBoard> aBoardList = aBoardDao.selectAll();
					// show menu
					while (true) {
						System.out.println("┌──────────┬──────────┬──────────┬──────────┬──────────┐");
						System.out.println("│　　1.읽기　　│ 2.새글작성　│　　3.수정　　│　　4.삭제　　│ 9.이전메뉴　│");
						System.out.println("└──────────┴──────────┴──────────┴──────────┴──────────┘");
						int menuF = Integer.parseInt(sc.nextLine());
						if (menuF == 1) {
							// show all list
							for (AnonymousBoard anonymousBoard : aBoardList) {
								System.out.println(anonymousBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							AnonymousBoard aBoard = null;
							// get one article by boardNum
							for (AnonymousBoard anonymousBoard : aBoardList) {
								if (anonymousBoard.getBoardNum() == select) {
									aBoard = anonymousBoard;
								}
							}
							// show content of selected
							System.out.println("글 번호\t제목");
							System.out.println(aBoard.getBoardNum() + "\t" + aBoard.getTitle());
							System.out.println("작성자 : " + aBoard.getPoster() + " " + aBoard.getPostDate()); // todo :
																											// randomise
																											// poster
																											// name
							System.out.println("\n" + aBoard.getContent());

						} else if (menuF == 2) {
							// new article
							AnonymousBoard aBoard = new AnonymousBoard();
							aBoard.setPoster(loggedInUser.getName());
							System.out.println("제목 >");
							String title = sc.nextLine();
							aBoard.setTitle(title);
							System.out.println("글 내용 >");
							String content = sc.nextLine();
							aBoard.setContent(content);
//							fBoard.setPostDate();				todo : dtf must be preceded

							aBoardDao.insert(aBoard);

						} else if (menuF == 3) {
							// update article
							// todo : authority handling
							// show all list
							for (AnonymousBoard anonymousBoard : aBoardList) {
								System.out.println(anonymousBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							AnonymousBoard aBoard = null;
							// get one article by boardNum
							for (AnonymousBoard anonymousBoard : aBoardList) {
								if (anonymousBoard.getBoardNum() == select) {
									aBoard = anonymousBoard;
								}
							}
							// update that article
							System.out.println("제목 > ");
							String newTitle = sc.nextLine();
							aBoard.setTitle(newTitle);
							System.out.println("글 내용 > ");
							String newContent = sc.nextLine();
							aBoard.setContent(newContent);
//							fBoard.setPostDate();				todo : dtf must be preceded

							aBoardDao.update(aBoard);

						} else if (menuF == 4) {
							// delete article
							// todo : authority handling
							// show all list
							for (AnonymousBoard anonymousBoard : aBoardList) {
								System.out.println(anonymousBoard);
							}
							// select an article
							System.out.println("글 번호 > ");
							int select = Integer.parseInt(sc.nextLine());
							AnonymousBoard aBoard = null;
							// get one article by boardNum
							for (AnonymousBoard anonymousBoard : aBoardList) {
								if (anonymousBoard.getBoardNum() == select) {
									aBoard = anonymousBoard;
								}
							}
							// delete
							// todo : delete check
							aBoardDao.delete(aBoard);

						} else if (menuF == 9) {
							// return to previous menu
							System.out.println("이전 메뉴로 이동합니다.");
							break;
						}
					}
//				===============================================================================
					// search user page
				} else if (menu2 == 3) {
					ProjectDAO<Letter> letterDao = LetterDAOImpl.getInstance();
					List<Letter> letterList = letterDao.selectAll();
					while (true) {
						// show menu
						System.out.println("┌───────────┬────────────┬────────────┬────────────┬────────────┐");
						System.out.println("│ 1.사용자검색	│ 2.쪽지보내기	 │ㅤ3.받은쪽지함ㅤ│ㅤ4.보낸쪽지함ㅤ│　 9.이전메뉴  │");
						System.out.println("└───────────┴────────────┴────────────┴────────────┴────────────┘");
						int menuU = Integer.parseInt(sc.nextLine());
						if (menuU == 1) {
							// search user
							System.out.println("검색할 이름 > ");
							String who = sc.nextLine();
							User userResult = null;
							for (User user : userList) {
								if (user.getName().equals(who)) {
									userResult = user;
								}
							}

							// show user information(name, phone number)
							System.out.println(userResult); // todo : notify when there is no result

						} else if (menuU == 2) {
							// send letter
							Letter letter = new Letter();
							letter.setSender(loggedInUser.getName());
							
							// select receiver
							System.out.println("받는 사람 > ");
							String recipient = sc.nextLine();
							User userResult = null;
							for (User user : userList) {
								if (user.getName().equals(recipient)) {
									userResult = user;
								}
							}
							letter.setRecipient(userResult.getName());
							System.out.println("내용 > ");
							String content = sc.nextLine();
							letter.setContent(content);
//							letter.setTimestamp();	// todo : dtf must be preceded
							
							letterDao.insert(letter);
							
							
							
//							쪽지보내기까지 만듦
//							쪽지 번호 보낸 편지함 받는편지함 각각 1부터 시작
//							쪽지 일련번호는 숨기기
//							시간 체크
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
						}
					}
					// show received letters / show sent letters

				} else if (menu2 == 4) {
					// get back to sign in step

				}

			}

			sc.close();
		}
	}
}
