package BoardJDBC;

import java.util.List;
import java.util.Scanner;

public class BoardController {

	private Scanner scan;
	private Service svc;
	private boolean flag;
	
	public BoardController() {
		scan = new Scanner(System.in);
		svc = new BoardServiceImpl();
		flag = true;
		printMenu();
	}

	private void printMenu() {
		// 메뉴출력 메서드
		while(flag) {
			System.out.println("----게시판----");
			System.out.println("1.글쓰기|2.글목록보기|3.글상세보기");
			System.out.println("4.글수정|5.글삭제|6.종료");
			System.out.println(">> 메뉴선택");
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1: addBoard(); break;
			case 2: showBoardList(); break;
			case 3: showOneBoard(); break;
			case 4: modifyBoard(); break;
			case 5: removeBoard(); break;
			default: flag=false; break;
			}
		}
		
	}


	private void addBoard() {
		// 게시글 등록
		//글 쓰기용 : title, writer, content
		System.out.println("제목을 입력해주세요.");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("작성자를 입력해주세요.");
		String writer = scan.nextLine();
		System.out.println("내용을 입력해주세요.");
		String content = scan.nextLine();
		
		BoardVO bo = new BoardVO(title, writer, content);
		
		int isOk = svc.addBoard(bo);
		System.out.println("게시글 등록 "+(isOk>0? "성공":"실패"));
	}
	
	private void showBoardList() {
		// 게시글 목록 보기
		// 등록되어 있는 게시글 전체 목록을 출력
		// 글  목록보기용 : bno, title, writer, moddate
		List<BoardVO> list = svc.list();
		for(BoardVO a : list) {
			System.out.println(a);
		}
	}
	
	private void showOneBoard() {
		// 게시글 상세보기, 내용까지 출력되어야 함
		// 전체 출력 : bno, title, writer, moddate
		System.out.println("확인하고 싶은 게시글 번호를 입력해주세요.");
		int bno = scan.nextInt();
		autoIncreCount(bno);
		BoardVO bo = svc.detail(bno);
		bo.printDetail();
	}
	
	private void modifyBoard() {
		// 게시글 내용 수정하기
		System.out.println("수정할 게시글 번호를 입력해주세요.");
		int bno = scan.nextInt();
		System.out.println("제목 수정 >> ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("내용 수정 >> ");
		String content = scan.nextLine();
		
		BoardVO bo = new BoardVO(bno, title, content);
		
		int isOk = svc.modify(bo);
		System.out.println("게시글 수정 "+(isOk>0? "성공":"실패"));
	}
	
	private void removeBoard() {
		// 게시글 삭제 메서드
		System.out.println("삭제할 게시글 번호를 입력해주세요.");
		int bno = scan.nextInt();
		
		int isOk = svc.remove(bno);
		System.out.println("게시글 삭제 "+(isOk>0? "성공":"실패"));
	}
	
	private void autoIncreCount(int bno) {
		svc.autoCount(bno);
	}
}
