package new01;

import java.util.HashMap;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterMakeAndModel;

public class New05 {

	public static void main(String[] args) {
		/* new04 클래스의 단어장을 메서드로 분리
		 * 메서드에서 리턴한 map을 받아서 출력  
		 * */
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> myword = make(sc);
		
		int idx = 1;
		for(String tmp : myword.keySet()) {
			System.out.println(idx++ + ". " + tmp + ": " + myword.get(tmp));
		}
		
		sc.close();
	}

	
	// 기능 : 단어:의미를 입력받아 Map을 구성
	// Map은 메서드 안에서 생성하여 리턴
	
	public static HashMap<String, String> make(Scanner sc) {
		HashMap<String, String> map = new HashMap<String, String>();
		int cnt = 0;
		String word = "";
		String mean = "";
		
		System.out.println("단어장에 들어갈 단어 갯수를 입력해주세요.");
		System.out.print("개수 : ");
		System.out.println();
		while (true) {
			cnt = sc.nextInt();
			if(cnt<1){
				System.out.println("단어장 개수는 1보다 작을수 없습니다. 숫자를 다시 입력해주세요.");
				System.out.print("개수 : ");
			} else {
				break;
			}
		}
		
		for(int i=0; i<cnt; i++) {
			System.out.println("단어를 입력해주세요.");
			word = sc.next();
			System.out.println("단어의 의미를 입력해주세요.");
			mean = sc.next();
			map.put(word, mean);
		}
		
		return map;
	}
}
