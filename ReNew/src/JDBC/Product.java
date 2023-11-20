package JDBC;

public class Product {
	
	private int pno;
	private String pname;
	private int price;
	private String regdate;
	private String madeby;
	
	public Product() {}
	// 생성자 처리를 해야 nullpoint Exception 처리가 가능
	
	// 상품등록 -> pname, price, madeby
	public Product(String pname, int price, String madeby) {
		this.pname =  pname;
		this.price = price;
		this.madeby = madeby;
	}
	
	// 상품리스트 -> pno, pname, price
	public Product(int pno, String pname, int price) {
		this.pno = pno;
		this.pname = pname;
		this.price = price;
	}
	
	// 상품상세 -> all
	public Product(int pno, String pname, int price, String regdate, String madeby) {
		this.pno = pno;
		this.pname = pname;
		this.price = price;
		this.regdate = regdate;
		this.madeby = madeby;
	}
	
	// 상품수정 -> pno, pname, price, madeby
	public Product(int pno, String pname, int price, String madeby) {
		this.pno = pno;
		this.pname = pname;
		this.price = price;
		this.madeby = madeby;
	}

	@Override
	public String toString() {
		return "Product [pno=" + pno + ", pname=" + pname + ", price=" + price + ", regdate=" + regdate + ", madeby="
				+ madeby + "]";
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getMadeby() {
		return madeby;
	}

	public void setMadeby(String madeby) {
		this.madeby = madeby;
	}
}
