package project_Yedam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_Yedam.VO.Article;
import project_Yedam.VO.BoardType;
import project_Yedam.VO.User;
import project_Yedam.dao.ArticleDAOImpl;
import project_Yedam.dao.ProjectDAO;
import project_Yedam.dao.UserDAOImpl;

public class BoardPage {

	// singleton
	private static BoardPage instance = new BoardPage();
	private BoardPage() {}
	public static BoardPage getInstance(User loggedInUser) {
		BoardPage.loggedInUser = loggedInUser;
		return instance;
	}

	// field
	private Scanner sc = new Scanner(System.in);
	private ProjectDAO<Article, String> articleDao = ArticleDAOImpl.getInstance();
	private ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
	private static User loggedInUser = null;
	private String boardType = null;
	private Main console = new Main();
	

//	private static int freeBoardSerial = 0;
//	private static int anonymousBoardSerial = 0;

	// methods
	
	public String getBoardType() {
		return boardType; 
	}
	
	public void setBoardType(BoardType boardType) {
		switch (boardType) {
		case FREE:
			this.boardType = "자유게시판";
			break;
		case ANONYMOUS:
			this.boardType = "익명게시판";
			break;
		}
	}
	
	
	
	
	public void printBoard(User loggedInUser, BoardType boardType) {
		
		setBoardType(boardType);
		
		while (true) {
			
			int menu = 0;
			// show all list
			showArticleList();
			
			menu = printMenu();	// 1.읽기 2.새글작성 3.수정 4.삭제 9.이전메뉴
			
			if (menu == 1) {
				Article article = selectArticle();
				readArticle(article);
				
			} else if (menu == 2) {
				// new article
				newArticle();
				
			} else if (menu == 3) {
				// update article
				updateArticle();
				
			} else if (menu == 4) {
				// delete article
				deleteArticle();
				
			} else if (menu == 9) {
				// return to previous menu
				
				console.clear();
				System.out.println("이전 메뉴로 이동합니다.\n");
				break;
			}
		}
	}

	public void showArticleList() {

		List<Article> articleList = articleDao.selectAll();

		try {
			printArticleListFormat(articleList, loggedInUser.getAuthority());
		} catch (Exception e) {
			System.out.println("error from BoardPage.showArticleList()\n");
		}
	}

	void printArticleListFormat(List<Article> articleList, String authority) {


//		System.out.println("=============================== " + boardType + "게시판 =================================\n");
		System.out.println("\n┌────────────────────────────── " + boardType + " ────────────────────────────────┐\n");
		System.out.printf("%6s %-4s\t%5s%-5s   %-3s   %10s%-10s\n", "번호", " 이름", "날", "짜", "조회수", "제", "목");

		printArticleList(articleList, "admin"); // admin article
		System.out.println("------------------------------------------------------------------------");
		printArticleList(articleList, "general"); // general article

	}

	void printArticleList(List<Article> articleList, String authority) {
		for (Article article : articleList) {
			if (userDao.selectOne(article.getPosterId()).getAuthority().equals(authority)
					&& article.getBoardType().equals(boardType)) {
				article.toList(loggedInUser, boardType);
			}
		}
	}

	public int printMenu() {

		System.out.println();
//		System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
		System.out.println("      1.읽기       2.새 글 작성       3.수정        4.삭제       9.이전메뉴      ");
		System.out.println("└───────────────────────────────────────────────────────────────────────┘");

		int menu;
		while (true) {
			try {
				System.out.print("\n>> ");
				String input = null;
				input = sc.nextLine();
				menu = Integer.parseInt(input);
				
			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}
			
			if ((menu == 1) || (menu == 2) || (menu == 3) || (menu == 4) || (menu == 9)) {
				return menu;
			}

			System.err.println("\n잘못 입력하셨습니다.\n");
		}
	}

	public Article selectArticle() {

		// select an article
		Article article = articleDao.selectOne(boardType);
		
		console.clear();

		return article;
	}

	public void readArticle(Article article) {

		try {
			// pageView++
			article.setPageView(article.getPageView() + 1);
			
			articleDao.update(article);

			// print selected article
			printArticle(article);
			
		} catch (Exception e) {
			System.err.println("잘못 선택하셨습니다.");
		}

	}
	
	void printArticle(Article article) {
		
		System.out.println("\n┌──────────────────────────────── 글 읽기 ─────────────────────────────────┐\n");
		System.out.println("제목\t" + article.getTitle());

		// show userName only for administrator and my article
		User poster = userDao.selectOne(article.getPosterId());
		if (boardType.equals("익명") && !(loggedInUser.getAuthority().equals("admin")
				|| article.getPosterId().equals(loggedInUser.getId()))) {
			poster.setName("김 아무개");
		}

		System.out.println("작성자\t" + poster.getName() + "\t\t작성일\t" + article.printPostTime(3).substring(0,19) + "\t조회수\t" + article.getPageView());
		System.out.println("\n-     -     -     -     -     -     -     -     -     -     -     -     -");
		System.out.println("\n" + article.getContent());
		System.out.println("\n-     -     -     -     -     -     -     -     -     -     -     -     -");
		System.out.println("\n추천 [ " + article.getLikeNum() + " ]\t비추천 [ " + article.getUnlikeNum() + " ]");
		System.out.println("\n-------------------------------------------------------------------------\n");
		
	}

	
	boolean checkAuthority(Article article) {

		if (!(loggedInUser.getAuthority().equals("admin") || article.getPosterId().equals(loggedInUser.getId()))) {
			System.out.println("권한이 없습니다.");
			return false;

		} else {
			return true;
		}
	}

	public void newArticle() {
		
		console.clear();
		
		System.out.println("\n┌────────────────────────────── 새 글 작성 ────────────────────────────────┐\n");
		switch (boardType) {
		case "자유": // need boardType, articleNum, poster, title, content, postTime

			try {
				// boardType, articleNum, postTime by constructor
				// freeBoardSerial++;
				Article articleFB = new Article(boardType);

				// poster
				articleFB.setPosterId(loggedInUser.getId());
				articleFB.setPosterName(loggedInUser.getName());

				// articleFB.setTitle();
				if (newTitle(articleFB) == null) {
					return; // canceled newArticle in newTitle()
				}
				System.out.println();

				// articleFB.setContent();
				if (newContent(articleFB) == null) {
					return; // canceled newArticle in newContent()
				}

				articleDao.insert(articleFB);
				System.out.println("새 글이 등록되었습니다.");

			} catch (Exception e) {
				System.err.println("새 글 작성 중 오류가 발생했습니다.");
			}

			break;

		case "익명": // need boardType, articleNum, poster, title, content, postTime

			try {
				// boardType, articleNum, postTime by constructor
//			anonymousBoardSerial++;
				Article articleAB = new Article(boardType);

				// poster
				articleAB.setPosterId(loggedInUser.getId());
				articleAB.setPosterName(loggedInUser.getName());

				// articleAB.setTitle();
				if (newTitle(articleAB) == null) {
					return; // canceled newArticle in newTitle()
				}
				System.out.println();

				// articleAB.setContent();
				if (newContent(articleAB) == null) {
					return; // canceled newArticle in newContent()
				}

				articleDao.insert(articleAB);
				System.out.println("새 글이 등록되었습니다.");

			} catch (Exception e) {
				System.err.println("새 글 작성 중 오류가 발생했습니다.");
			}

			break;

		default:
			System.out.println("새 글 작성 중 오류");
		}
	}

	Article newTitle(Article article) {
		Title: while (true) {
			System.out.print("글 제목을 입력하세요.\n>> ");
			String title = sc.nextLine();
			if (title.equals("")) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			// confirmation
			while (true) {
				int menu;
				try {
					System.out.println("\n           1.확인              2.다시 작성하기            9.글쓰기 취소         ");
					System.out.println("└────────────────────────────────────────────────────────────────────────┘");
					System.out.print(">> ");

					menu = Integer.parseInt(sc.nextLine());

				} catch (Exception e) {
					System.err.println("\n잘못 입력하셨습니다.");
					continue;
				}
				if (menu == 1) {
					article.setTitle(title);
					return article;
				} else if (menu == 2) {
					console.clear();
					System.out.println("\n다시 작성합니다.\n");
					System.out.println("\n┌────────────────────────────── 새 글 작성 ────────────────────────────────┐\n");
					continue Title;
					
				} else if (menu == 9) {
					console.clear();
					System.out.println("\n이전 메뉴로 돌아갑니다.\n");
					return null;
				}
				console.clear();
				System.err.println("\n없는 메뉴입니다.\n");
			}
		}
	}

	Article newContent(Article article) {
		Content: while (true) {
			// content
			System.out.println("글 내용을 입력하세요.");
			System.out.println("작성을 종료하려면 \"작성종료\"를 입력하세요.");
			System.out.println("이전 줄을 지우려면 \"행삭제\"를 입력하세요.");

			String content = "";
			List<String> lines = new ArrayList<>();

			ContentBuilder: while (true) {
				System.out.print(">> ");
				String input = sc.nextLine();

				if (input.equals("작성종료") || input.equals("행삭제")) {
					if (lines.size() == 0) {
						System.out.println("\n작성을 종료합니다.");
						article.setContent(content);
						return article;

					} else if (input.equals("작성종료")) {
						// confirmation
						while (true) {
							int menu;
							try {
								System.out.println("\n     1.확인       2.계속 작성하기        3.새로 작성하기       9.글쓰기 취소      ");
								System.out.println("└───────────────────────────────────────────────────────────────────────┘");
								System.out.print("\n>> ");
								menu = Integer.parseInt(sc.nextLine());
								
								console.clear();

							} catch (Exception e) {
								System.err.println("\n잘못 입력하셨습니다.\n");
								continue;
							}
							if (menu == 1) {
								for (String string : lines) {
									content += string;
								}
								article.setContent(content);
								System.out.println("\n작성을 완료합니다.");
								return article;

							} else if (menu == 2) {
								
								System.out.println("\n이어서 작성합니다.\n");
								System.out.println("\n┌────────────────────────────── 새 글 작성 ────────────────────────────────┐\n");
								for (String string : lines) {
									System.out.print(string);
								}
								continue ContentBuilder;

							} else if (menu == 3) {
								System.out.println("\n새로 작성합니다.\n");
								System.out.println("\n┌────────────────────────────── 새 글 작성 ────────────────────────────────┐\n");
								continue Content;

							} else if (menu == 9) {
								System.out.println("\n이전 메뉴로 돌아갑니다.");
								return null;
							}
							System.err.println("\n없는 메뉴입니다.\n");
						}

					} else if (input.equals("행삭제")) {
						lines.remove(lines.size() - 1);
						
						console.clear();
						
						System.out.println("\n이전 행 삭제 완료\n");
						for (String string : lines) {
							System.out.print(string);
						}
					}

				} else {
					lines.add(input + "\n");
				}
			}
		}
	}

	public void updateArticle() {

		System.out.print("\n수정 할 ");
		Article article = selectArticle();

		if (!checkAuthority(article)) {
			return;
		}

		while (true) {
			int menu;
			try {
				printArticle(article);
				System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
				System.out.println("           1.제목수정              2.내용수정              9.취소              ");
				System.out.print("\n>> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}
			if (menu == 1) {
				System.out.println("\n");
				System.out.println("기존 제목 : " + article.getTitle() + "\n");

				System.out.print("새로운 ");
				Article updatedArticle = newTitle(article);
				if (updatedArticle != null) {
					article = updatedArticle;

					articleDao.update(article);
					System.out.println("제목을 수정했습니다.");
					continue;

				} else {
					continue; // canceled title update in newTitle()
				}

			} else if (menu == 2) {
				System.out.println("\n기존 내용 :\n\n" + article.getContent() + "\n");

				System.out.print("새로운 ");
				Article updatedArticle = newContent(article);
				if (updatedArticle != null) {
					article = updatedArticle;

					articleDao.update(article);
					System.out.println("내용을 수정했습니다.");
					continue;

				} else {
					continue; // canceled content update in newContent()
				}

			} else if (menu == 9) {
				System.out.println("\n이전 메뉴로 돌아갑니다.");
				return;
			}
			System.err.println("\n없는 메뉴입니다.\n");
		}

	}

	public void deleteArticle() {

		System.out.print("\n삭제 할 ");
		Article article = selectArticle();

		if (!checkAuthority(article)) {
			return;
		}

		System.err.println("삭제 후에는 복구할 수 없습니다. 삭제하시겠습니까?\n");

		while (true) {
			System.out.println("                    1.삭제                     9.취소                     ");
			System.out.println("└───────────────────────────────────────────────────────────────────────┘");
			System.out.print("\n>> ");
			int select = 0;

			try {
				select = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			if (select == 1) {
				console.clear();
				articleDao.delete(article);
				System.out.println("\n삭제되었습니다.");	
				
				
			} else if (select == 9) {
				console.clear();
				System.out.println("\n이전 메뉴로 돌아갑니다.");
				return;

			} else {
				System.err.println("\n없는 메뉴입니다.\n");
			}
		}
	}
}
