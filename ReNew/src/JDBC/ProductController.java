package JDBC;

import java.util.List;
import java.util.Scanner;

public class ProductController {
	/* 상품등록, 상품 리스트 보기, 상품상세보기, 상품수정, 상품 삭제
	 * 구조 : Controller <-> Service <-> DAO <-> DB
	 * Controller에서 분기처리 모든 메뉴를 처리
	 * */
	
	private Scanner scan;
	private Service svc;  // 아직 안 만듬  package에 interface로 생성
	private boolean flag;  // 종료 변수
	
	public ProductController() {
		scan = new Scanner(System.in);
		svc = new ProductServiceImpl();  // service 구현체
		flag = true;
		printMenu();  // 메뉴 출력
	}

	private void printMenu() {
		// flag가 트루면 계속 메뉴 출력
		while (flag) {
			System.out.println("---상품관리 프로그램---");
			System.out.println("1.상품등록|2.상품목록|3.상품검색(상세)");
			System.out.println("4.상품수정|5.상품삭제|6.종료");
			System.out.println("Menu >> ");
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1: regiset(); break;
			case 2: list(); break;
			case 3: detail(); break;
			case 4: modify(); break;
			case 5: remove(); break;
			default: flag=false; break;
			}
			System.out.println("프로그램 종료");
		}
	}

	private void regiset() {
		// 상품등록
		System.out.println("상품명 : ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.println("가격 : ");
		int price = scan.nextInt();
		System.out.println("상품상세내역 : ");
		scan.nextLine();
		String madeby = scan.nextLine();
		
		Product p = new Product(name, price, madeby);
		// svc에게 등록을 요청하는 메서드 작성
		// insert를 하게되면 리턴되는 값은 몇개의 행이 insert 되었는지 값이 리턴
		// isOk insert 후 리턴되는 값을 저장 잘 되면 1, 안되면 0이 리턴
		int isOk = svc.register(p);
		System.out.println("상품등록" + ((isOk > 0) ? "성공" : "실패"));
	}
	
	private void list() {
		// 상품전체 리스트 select * from product;
		List<Product> list = svc.list();
		
		for(Product p : list) {
			System.out.println(p);
		}
	}
	
	private void detail() {
		// 상품 상세 정보 select * from product where pno = 1;
		System.out.println("상품번호 > ");
		 int pno = scan.nextInt();
		 Product p = svc.detail(pno); 
		 System.out.println(p);
		
		
	}
	
	private void modify() {
		// TODO Auto-generated method stub
		
	}
	
	private void remove() {
		// TODO Auto-generated method stub
		
	}

}
