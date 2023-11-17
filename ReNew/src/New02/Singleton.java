package New02;

public class Singleton {

	public static void main(String[] args) {
		// 싱글톤 패턴 : 디자인 패턴 중 하나
		// 객체를 1개만 생성하여 공유하고자 할 때 사용
		// 하나의 객체만 생성해서 그 객체를 그대로 이용하고자 할 때, DB 쓸 때 많이 사용함
		
		
		Single s = Single.getInstance();
		Single s2 = Single.getInstance();
		
		System.out.println(s);
		System.out.println(s2);
		// 위의 출력하는 객체 주소가 같음
		
		if(s == s2) {
			System.out.println("s와 s2는 다른 객체");
		} else {
			System.out.println("s와 s2는 다른 객체");
		}
		
	}

}


class Single {
	
	// 객체를 1개만 생성해서 공유하고자 하는 목적임
	private static Single instance;

	
	// 생성자를 통한 객체 생성을 막기위해 기본 생성자를 private 설정
	private Single() {}
	
	// getInstance가 getter 역할을 수행
	// getInstance() 메서드를 사용하여 instance가 생성되지 않았다면 생성해서 리턴, 아니면 기존 인스턴스를 리턴
	public static Single getInstance() {
		if(instance == null) {  // 아직 객체가 생성되지 않았다면....
			instance = new Single();
		}
		
		
		return instance;
	}
	
	
}
