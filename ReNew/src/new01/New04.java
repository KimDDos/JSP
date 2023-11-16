package new01;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class New04 {

	public static void main(String[] args) {
		/* 단어장 만들기
		 * 단어장 만들 개수 
		 * 단어와 의미를 입력받고 출력하기
		 * */
		
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> myWord = new HashMap<String, String>();
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
			myWord.put(word, mean);
		}
		
		// Iterator 를 활용한 출력구문
		Iterator<String> it = myWord.keySet().iterator();
		while(it.hasNext()) {
			String wordValue = it.next();
			System.out.println(wordValue+": "+myWord.get(wordValue));
			
		}
		
		int idx = 1;
		for(String tmp : myWord.keySet()) {
			System.out.println(idx++ +". "+tmp+" : "+myWord.get(tmp));
		}
		
		
		
		
		sc.close();
	}

}
