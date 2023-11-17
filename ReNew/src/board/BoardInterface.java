package board;

import java.util.Scanner;

public interface BoardInterface {
	void printMenu();
	void addBoard(Scanner scan);  // 게시글 등록
	void searchBoard(Scanner scan);  // 게시글 조회
	void modifyBoard(Scanner scan);  // 게시글 수정
	void removeBoard(Scanner scan);  // 게시글 삭제
	void printBoard();  // 게시글 전체 출력
}
