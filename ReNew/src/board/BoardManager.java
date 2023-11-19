package board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.border.Border;

public class BoardManager {

	ArrayList<Board> boardArr = new ArrayList<Board>();
	
//	void printMenu();
	public void printMenu() {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		do {
			printBoard();
			
			System.out.println("------------Ezen 게시판------------");
			System.out.println(" 1. 게시글 등록 | 2. 게시글 조회 ");
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
				searchBoard(sc);
				break;
			// 게시글 수정
			case 3:
				modifyBoard(sc);
				break;
			// 게시글 삭제
			case 4:
				removeBoard(sc);
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
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM-dd HH:mm:ss"));
		boardArr.add(new Board(boardTitle, boardContent, writer, date));
		System.out.println("게시글 작성 완료!");
		System.out.println("---------------------------------");
	}
//	void searchBoard(Scanner scan);  // 게시글 조회
	public void searchBoard(Scanner sc) {
		System.out.println("조회할 게시글 번호를 입력해주세요.");
		int searchNum = sc.nextInt();
		
		System.out.println("---------------조회결과----------------");
		for(Board a : boardArr) {
			if(a.getBoardNum() == searchNum) {
				System.out.println(a.toString());
				System.out.println("---------------------------------");
				break;
			} else {
				System.out.println("일치하는 게시글이 없습니다.");
				System.out.println("---------------------------------");
			}
		}
	}
//	void modifyBoard(Scanner scan);  // 게시글 수정
	public void modifyBoard(Scanner sc) {
		System.out.println("수정할 게시글 번호를 입력해주세요.");
		int searchNum = sc.nextInt();
		
		for(Board a : boardArr) {
			if(a.getBoardNum() == searchNum) {
				System.out.println(a.toString());
				System.out.println("게시글을 수정해주세요.");
				System.out.print("제목 : ");
				a.setBoardTitle(sc.next());
				System.out.print("내용 : ");
				a.setBoardContent(sc.next());
				a.setWritenDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("(수정) yyyy.MM-dd HH:mm:ss")));
				System.out.println("---------------------------------");
				break;
			} else {
				System.out.println("일치하는 게시글이 없습니다.");
				System.out.println("---------------------------------");
			}
		}
	}
	
//	void removeBoard(Scanner scan);  // 게시글 삭제
	public void removeBoard(Scanner sc) {
		System.out.println("삭제할 게시글 번호를 입력해주세요.");
		int searchNum = sc.nextInt();
		
		for(Board a : boardArr) {
			if(a.getBoardNum() == searchNum) {
				boardArr.remove(searchNum-1);
				System.out.println("게시글 삭제 성공!");
				System.out.println("---------------------------------");
				break;
			} else {
				System.out.println("해당 게시글이 없습니다.");
				System.out.println("---------------------------------");
			}
		}
	}
	
//	void printBoard();  // 게시글 전체 출력
	public void printBoard() {
		if(boardArr.size()==0) {
			System.out.println("등록된 게시글이 없습니다."); 
			System.out.println("---------------------------------");
			return; 
			}
		else {
			for(Board a : boardArr) {
				System.out.println(a.getBoardNum()+". 제목["+a.getBoardTitle()+"] | 작성자: ["+a.getWriter()+"] | 작성시간: ["+a.getWritenDate()+"]");
				}
			}
		System.out.println("---------------------------------");
	}
	
	
}
