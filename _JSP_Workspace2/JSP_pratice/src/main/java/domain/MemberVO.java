package domain;

public class MemberVO {
	
	private String id;
	private String pwd;
	private String email;
	private int age;
	private String regdate;
	private String lastlogin;
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", email=" + email + ", age=" + age + ", regdate=" + regdate
				+ ", lastlogin=" + lastlogin + "]";
	}

	// 회원가입(join) : id, pwd, email, age
	public MemberVO(String id, String pwd, String email, int age) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
	}

	// 회원목록 띄우기(list) : All
	public MemberVO(String id, String pwd, String email, int age, String regdate, String lastlogin) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
		this.regdate = regdate;
		this.lastlogin = lastlogin;
	}
	
	public MemberVO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	
}
