package word;

import java.io.IOException;
import java.util.Scanner;

public class WordMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		WordManager wm = new WordManager();
		
		// 조건식 종료 기준
		int menu = 0;
		
		do {
			System.out.println("---- 단 어 장 ----");
			System.out.println("1. 단어 등록");
			System.out.println("2. 단어 검색");
			System.out.println("3. 단어 수정");
			System.out.println("4. 단어 출력");
			System.out.println("5. 단어 삭제");
			System.out.println("6. 종료하기");
			System.out.print(" >> 선택 : ");
			menu = sc.nextInt();
			
			try {
				switch (menu) {
				case 1: 
					System.out.println("단어를 입력해주세요.");
					String word = sc.next();
					System.out.println("단어의 의미를 입력해주세요.");
					String mean = sc.next();
					wm.addWord(word, mean);	
					System.out.println("---------------------------------------");
					break;
				case 2:
					System.out.println("검색할 단어를 입력해주세요.");
					word = sc.next();
					wm.serchWord(word);
					System.out.println("---------------------------------------");
					break;
				case 3:
					System.out.println("수정할 단어를 입력해주세요.");
					word = sc.next();
					System.out.println("수정할 단어의 의미를 입력해주세요.");
					mean = sc.next();
					wm.rename(word, mean);
					System.out.println("---------------------------------------");
					break;
				case 4:
					System.out.println("단어장을 출력합니다.");
//				wm.printWordList();
					wm.filePrint();
					System.out.println("---------------------------------------");
					break;
				case 5:
					System.out.println("삭제할 단어를 입력해주세요.");
					word = sc.next();
					wm.deleteWord(word);
					System.out.println("---------------------------------------");
					break;
				case 6:
					System.out.println("단어장을 종료합니다.");
					break;
				default: break;
				}
			} catch (Exception e) {
				System.out.println("파일 출력 에러");
				e.printStackTrace();
				// TODO: handle exception
			}
			
		} while (menu != 6);
		System.out.println("단어장 종료!");
		sc.close();
	}

}
