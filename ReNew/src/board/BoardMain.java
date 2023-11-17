package board;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		// 게시판 데이터 처리 클래스
		// CRUD (C:Create(생성), R: Read(조회), U:Update(수정), D:Delete(삭제))
		// Board class(게시글번호, 제목, 내용, 작성자, 작성일)
		// 번호는 자동으로 카운트 되게 (게시글번호만 싱글톤 패턴으로 만들면 될)
		// 작성일은 현재 날짜 시간으로 들어가게 자동 설정.
		BoardManager bm = new BoardManager();
		
		bm.printMenu();
		
		
		

	}

}
