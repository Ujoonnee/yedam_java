package project_Yedam.VO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardPage {

	// singleton
	private static BoardPage instance = new BoardPage();
	private BoardPage() {}
	public static BoardPage getInstance() {
		return instance;
	}

	// field
	private Scanner sc = new Scanner(System.in);
	private ProjectDAO<Article, String> articleDao = ArticleDAOImpl.getInstance();
	private ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();

	private static int freeBoardSerial = 0;
	private static int anonymousBoardSerial = 0;

	
	// methods
	public String checkBoardType(BoardType boardType) {
		switch (boardType) {
		case FREE:
			return "free";

		case ANONYMOUS:
			return "anonymous";

		default:
			return null;
		}
	}

	public void showArticleList(String boardType) {

		List<Article> articleList = articleDao.selectAll();
		System.out.println();

		try {
			switch (boardType) {
			case "free":
				// administrator notice list
				for (Article article : articleList) {
					if (userDao.selectOne(article.getPosterId()).getAuthority().equals("admin")) {
						System.out.println(article);
					}
				}
				
				// general user article list
				for (Article article : articleList) {
					if (userDao.selectOne(article.getPosterId()).getAuthority().equals("general")) {
						System.out.println(article);
					}
				}
				break;
				
			case "anonymous":
				// administrator notice list
				for (Article article : articleList) {
					if (userDao.selectOne(article.getPosterId()).getAuthority().equals("admin")) {
						userDao.selectOne(article.getPosterId()).setName("🤐");
						System.out.println(article);
					}
				}
				
				// general user article list
				for (Article article : articleList) {
					if (userDao.selectOne(article.getPosterId()).getAuthority().equals("general")) {
						userDao.selectOne(article.getPosterId()).setName("🤐");
						System.out.println(article);
					}
				}
			}
				
		}  catch (Exception e) {
			System.out.println("error from BoardPage.showArticleList()\n");
		}
	}

	public int printMenu() {

		System.out.println();
		System.out.println("┌──────────┬──────────┬──────────┬──────────┬──────────┐");
		System.out.println("│　　1.읽기 　　 2.새글작성　　　3.수정　　 　　4.삭제　　 9.이전메뉴　│");
		System.out.println("└──────────┴──────────┴──────────┴──────────┴──────────┘");

		int menu;
		while (true) {
			try {
				System.out.print("메뉴 선택\n> ");
				menu = Integer.parseInt(sc.nextLine());

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

	public Article selectArticle(String boardType) {

		// select an article
		Article article = articleDao.selectOne(boardType);

		return article;
	}

	public void printArticle(User loggedInUser, Article article, String boardType) {

		// print selected article
		System.out.println("\n");
		System.out.println("제목\t" + article.getTitle());

		// show userName only for administrator and my article
		User poster = userDao.selectOne(article.getPosterId());
		if (boardType.equals("anonymous") && !(loggedInUser.getAuthority().equals("admin")
				|| article.getPosterId().equals(loggedInUser.getId()))) {
			poster.setName("🤐");
		}

		System.out.println("작성자\t" + poster.getName() + "\t\t작성일\t" + article.printPostTime(3));
		System.out.println("\n" + article.getContent());
		System.out.println("\n추천 [ " + article.getLikeNum() + " ]\t비추천 [ " + article.getUnlikeNum() + " ]");

	}

	boolean checkAuthority(User user, Article article) {

		if (!(user.getAuthority().equals("admin") || article.getPosterId().equals(user.getId()))) {
			System.out.println("권한이 없습니다.");
			return false;

		} else {
			return true;
		}
	}

	public void newArticle(String boardType, User loggedInUser) {
		switch (boardType) {
		case "free": // need boardType, articleNum, poster, title, content, postTime

			// boardType, articleNum, postTime by constructor
			freeBoardSerial++;
			Article articleFB = new Article("free", freeBoardSerial);

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

			break;

		case "anonymous": // need boardType, articleNum, poster, title, content, postTime

			// boardType, articleNum, postTime by constructor
			anonymousBoardSerial++;
			Article articleAB = new Article("anonymous", anonymousBoardSerial);

			// poster
			articleAB.setPosterId(loggedInUser.getId());
			articleAB.setPosterName(loggedInUser.getName());
			
			// articleAB.setTitle();
			if (newTitle(articleAB) == null) {
				return; // canceled newArticle in newTitle()
			}

			// articleAB.setContent();
			if (newContent(articleAB) == null) {
				return; // canceled newArticle in newContent()
			}

			articleDao.insert(articleAB);

			break;

		default:
			System.out.println("새 글 작성 중 오류");
		}
	}

	Article newTitle(Article article) {
		Title: while (true) {
			System.out.print("글 제목을 입력하세요.\n> ");
			String title = sc.nextLine();
			if (title.equals("")) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}

			// confirmation
			while (true) {
				int menu;
				try {
					System.out.print("\n1.확인 2.다시 작성하기 9.글쓰기 취소\n> ");
					menu = Integer.parseInt(sc.nextLine());

				} catch (Exception e) {
					System.err.println("\n잘못 입력하셨습니다.");
					continue;
				}
				if (menu == 1) {
					article.setTitle(title);
					return article;
				} else if (menu == 2) {
					System.out.println("\n다시 작성합니다.");
					continue Title;
				} else if (menu == 9) {
					System.out.println("\n이전 메뉴로 돌아갑니다.");
					return null;
				}
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
				System.out.print("> ");
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
								System.out.print("\n1.확인 2.계속 작성하기 3.새로 작성하기 9.글쓰기 취소\n> ");
								menu = Integer.parseInt(sc.nextLine());

							} catch (Exception e) {
								System.err.println("\n잘못 입력하셨습니다.\n");
								continue;
							}
							if (menu == 1) {
								article.setContent(content);
								articleDao.insert(article);
								System.out.println("\n작성을 완료합니다.");
								return article;

							} else if (menu == 2) {
								System.out.println("\n이어서 작성합니다.");
								for (String string : lines) {
									System.out.print(string);
								}
								continue ContentBuilder;

							} else if (menu == 3) {
								System.out.println("\n새로 작성합니다.");
								continue Content;

							} else if (menu == 9) {
								System.out.println("\n이전 메뉴로 돌아갑니다.");
								return null;
							}
							System.err.println("\n없는 메뉴입니다.\n");
						}

					} else if (input.equals("행삭제")) {
						lines.remove(lines.size() - 1);
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

	public void updateArticle(String boardType, User user) {

		System.out.print("수정 할 ");
		Article article = selectArticle(boardType);

		if (checkAuthority(user, article)) {
			return;
		}

		while (true) {
			int menu;
			try {
				System.out.println("\n1.제목수정 2.내용수정 9.취소\n> ");
				menu = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.err.println("\n잘못 입력하셨습니다.\n");
				continue;
			}
			if (menu == 1) {
				System.out.println("\n");
				System.out.println("기존 제목 : " + article.getTitle() + "\n");

				Article updatedArticle = newTitle(article);
				if (updatedArticle != null) {
					article = newTitle(updatedArticle);

					articleDao.update(article);
					System.out.println("제목을 수정했습니다.");

				} else {
					continue; // canceled title update in newTitle()
				}

			} else if (menu == 2) {
				System.out.println("\n");
				System.out.println("기존 내용 :\n" + article.getContent() + "\n");

				Article updatedArticle = newTitle(article);
				if (updatedArticle != null) {
					article = newContent(article);

					articleDao.update(article);
					System.out.println("내용을 수정했습니다.");

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

	public void deleteArticle(String boardType, User user) {

		System.out.print("삭제 할 ");
		Article article = selectArticle(boardType);

		if (checkAuthority(user, article)) {
			return;
		}

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
				articleDao.delete(article);
				System.out.println("\n삭제되었습니다.");

			} else if (select == 9) {
				System.out.println("\n이전 메뉴로 돌아갑니다.");
				return;

			} else {
				System.err.println("\n없는 메뉴입니다.\n");
			}
		}
	}
}
