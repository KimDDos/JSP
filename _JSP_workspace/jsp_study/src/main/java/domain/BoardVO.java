package domain;

public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String contetn;
	private String regdate;
	private String moddate;
	private int readCount;
	
	public BoardVO() {}

	
	// insert : title, writer, content
	public BoardVO(String title, String writer, String contetn) {
		super();
		this.title = title;
		this.writer = writer;
		this.contetn = contetn;
	}
	
	// list : bno, title, writer, regdate, readCount
	public BoardVO(int bno, String title, String writer, String regdate, int readCount) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.readCount = readCount;
	}

	// update : bno, title, content
	public BoardVO(int bno, String title, String contetn) {
		super();
		this.bno = bno;
		this.title = title;
		this.contetn = contetn;
	}
	
	// detail : all
	public BoardVO(int bno, String title, String writer, String contetn, String regdate, String moddate,
			int readCount) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.contetn = contetn;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readCount = readCount;
	}
	
	// toString
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", contetn=" + contetn + ", regdate="
				+ regdate + ", moddate=" + moddate + ", readCount=" + readCount + "]";
	}



	// getter / setter
	
	public int getBno() {
		return bno;
	}
	
	
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getWriter() {
		return writer;
	}
	
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	public String getContetn() {
		return contetn;
	}
	
	
	public void setContetn(String contetn) {
		this.contetn = contetn;
	}
	
	
	public String getRegdate() {
		return regdate;
	}
	
	
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	public String getModdate() {
		return moddate;
	}
	
	
	public void setModdate(String moddate) {
		this.moddate = moddate;
	}
	
	
	public int getReadCount() {
		return readCount;
	}
	
	
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	
}
