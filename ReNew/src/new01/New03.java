package new01;

import java.util.*;

public class New03 {

	public static void main(String[] args) {
		/* 컬렉션 프레임워크
		 * 
		 * List, Set, Map
		 * - List : 순서를 보장, 중복저장 가능, 배열과 동일
		 * - Set : 순서를 보장하지 않음, 중복저장 안 됨
		 * - Map : 2가지 데이터를 쌍으로 저장, 순서보장 X
		 *    - Key(중복불가능), value(중복가능)
		 * */
		
		// 리스트를 생성하고 1부터 10까지 저장한 후 출력
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=1; i<=10; i++) {
			list.add(i);
		}
		System.out.println(list);
		
		for(int a : list) {
			System.out.print(a+" ");
		}
		System.out.println();
		System.out.println(list.get(5));
		
		
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put("홍길동", 98);
		map.put("이순신", 89);
		map.put("강감찬", 77);
		
		System.out.println(map);
		
		for(String tmp : map.keySet()) {
			System.out.println(tmp+" : "+map.get(tmp));
		}
	}
	
}
