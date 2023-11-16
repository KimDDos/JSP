package new01;

public class New01 {

	public static void main(String[] args) {
		/* 홍길동 주민등록번호는 881122-1234567 
		 * 출력형태 => 생년월일 : 881122, 성별 : 남(1,3) / 여(2,4)
		 * */
		
		String pin = "881122-1234567";
		String birth = pin.substring(0, pin.indexOf('-'));
		System.out.println(birth);
		String ch = pin.substring(pin.indexOf('-')+1, pin.indexOf('-')+2);
		System.out.println(ch);
		// char '' ==  /  String "" equals(value)
		
		if(ch.equals("1") || ch.equals("3")) {
			System.out.println("생년월일 : " + birth + ", 성별 : 남");
		} else {
			System.out.println("생년월일 : " + birth + ", 성별 : 여");
		}
		
		
		
		// "-" 다음에 오는 숫자 : num1 변수 지정
		int num1 = pin.indexOf('-')+1;
		
		if(pin.substring(num1, num1+1).equals("1") || pin.substring(num1, num1+1).equals("3")) {
			System.out.println("생년월일 : " + pin.substring(0, num1-1)+", 성별 : 남");
		} else {
			System.out.println("생년월일 : " + pin.substring(0, num1-1)+", 성별 : 여");
		}
		
	}

}
