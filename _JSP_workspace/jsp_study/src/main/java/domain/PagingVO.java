package domain;

public class PagingVO {

	private int pageNo;  // 화면에 출력되는 페이지네이션 번호
	private int qty;  // 한 페이지에 보여줄 게시글 수 (10개)
	
	// 검색 멤버변수 추가
	private String type;
	private String keyword;
	
	public PagingVO() {  // 처음 리스트로 들어갔을 때 규칙
		// ㅠㅔ이지 네이션을 클릭하기 전 기본값 설정
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}	
	
	public int  getPageStart() {  // db에서 사용되는 시작 번호
		// 1페이지 => 0 / 2 => 10 / 3 => 20
		return (pageNo-1)*qty;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
}
