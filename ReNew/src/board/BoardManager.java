package board;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardManager {

	ArrayList<Board> boardArr = new ArrayList<Board>();
	
//	void printMenu();
	public void printMenu() {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		do {
			printBoard();
			
			System.out.println("------------Ezen 게시판------------");
			System.out.println(" 1. 게시글 등록 | 2. 게시글 수정 ");
			System.out.println(" 3. 게시글 수정 | 4. 게시글 삭제 ");
			System.out.println(" 5. 게시판 종료");
			System.out.print("  >> 선택 : ");
			menu = sc.nextInt();
			
			switch (menu) {
			// 게시글 추가
			case 1: 
				addBoard(sc);
				break;
			// 게시글 조회
			case 2:
				System.out.println("getBoardContent"+boardArr.get(0).getBoardContent());
				System.out.println("getBoardNum"+boardArr.get(0).getBoardNum());
				System.out.println("getBoardTitle"+boardArr.get(0).getBoardTitle());
				System.out.println("getWritenDate"+boardArr.get(0).getWritenDate());
				System.out.println("getWriter"+boardArr.get(0).getWriter());
//				searchBoard(sc);
				break;
			// 게시글 수정
			case 3:
//				modifyBoard(sc);
				break;
			// 게시글 삭제
			case 4:
//				removeBoard(sc);
				break;
			case 5: System.out.println("게시판을 종료합니다."); break;
			default: System.out.println("잘못된 입력입니다."); break;
			}
		} while (menu != 5);
		System.out.println("종료성공");
		sc.close();
	}
//	void addBoard(Scanner scan);  // 게시글 등록
	public void addBoard(Scanner sc) {
		System.out.print("제목 : ");
		String boardTitle = sc.next();
		System.out.print("작성자 : ");
		String writer = sc.next();
		System.out.print("내용 : ");
		String boardContent = sc.next();
		boardArr.add(new Board(boardTitle, boardContent, writer));
	}
//	void searchBoard(Scanner scan);  // 게시글 조회
//	void modifyBoard(Scanner scan);  // 게시글 수정
//	void removeBoard(Scanner scan);  // 게시글 삭제
	
//	void printBoard();  // 게시글 전체 출력
	public void printBoard() {
		if(boardArr.size()==0) System.out.println("등록된 게시글이 없습니다.");
		for(int i=0; i<boardArr.size(); i++) {
			boardArr.get(i).toString();
		}
	}
	
	
}
