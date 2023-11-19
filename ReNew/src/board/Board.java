package board;

import java.util.Calendar;

class BoardCounter{
	private static BoardCounter instance;
	private int count = 1;
	
	private BoardCounter() {}

	public static BoardCounter getInstance() {
		if(instance == null) {
			instance = new BoardCounter();
		}
		return instance;
	} 
	
	public int BoardCount() {
		return count++;
	}
}

public class Board {
	// Board class(게시글번호, 제목, 내용, 작성자, 작성일)
	private int boardNum;
	private String boardTitle, boardContent, writer, writenDate;
	
	
	public Board(String boardTitle,String boardContent,String writer, String date) {
		this.boardNum = BoardCounter.getInstance().BoardCount();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writer = writer;
		this.writenDate = date;
	}


	@Override
	public String toString() {
		return "[ 게시글 제목 : " + boardTitle.toString() + "]\r\n" + "[게시글번호("+ boardNum +") 작성자 : "+writer+" | 작성일자 : "+ writenDate + "]\r\n" + "[내용 : " +boardContent+"]";
		
//		return "Board [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
//				+ ", writer=" + writer + ", writenDate=" + writenDate + "]";
	}


	public int getBoardNum() {
		return boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getWriter() {
		return writer;
	}


	public String getWritenDate() {
		return writenDate;
	}


	public void setWritenDate(String writenDate) {
		this.writenDate = writenDate;
	}

	
	
}
