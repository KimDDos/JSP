package New02;

import java.util.Calendar;

class Counter{
	// 싱글톤 객체로 생성
	private static Counter instance;
	private int count = 0;
	
	private Counter() {}
	
	public static Counter getinstance() {
		if(instance == null) {
			instance = new Counter();
		}
		
		return instance;
	}
	
	public int countMethod() {
		return ++count;
	}
}

public class SingletonCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Counter c1 = Counter.getinstance();
		Counter c2 = Counter.getinstance();
		
		System.out.println(c1.countMethod());
		System.out.println(c2.countMethod());
		System.out.println(c1.countMethod());
		System.out.println(c2.countMethod());
		System.out.println(c1.countMethod());
		System.out.println(c2.countMethod());
		
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		System.out.println(year);
		
	}

}
