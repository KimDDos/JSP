package New02;

import java.io.IOException;
import java.net.URL;

public class NetWork02 {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=104"); 
		
		System.out.println(url.getContent());
		System.out.println(url.getAuthority());
		System.out.println(url.getPort());
		// -1 로 미제공
		System.out.println(url.getProtocol());
		System.out.println(url.getHost());
		System.out.println(url.getQuery());
		// ? 기준 뒤에 있는 String을 따옴
		System.out.println(url.getPath());
		
	}
	
}
