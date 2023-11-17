package word;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordManager {

	ArrayList<Word> wordList = new ArrayList<Word>();
	
	
	// 단어 추가 메서드
	public void addWord(String word, String mean) {
		wordList.add(new Word(word, mean));
	}
	
	// 단어 검색 메서드
	public void serchWord(String word) {
		if(wordList.size()==0) {System.out.println("저장된 단어가 없습니다."); return;}
		for(int i=0; i<wordList.size(); i++) {
			if(wordList.get(i).getWord().equals(word)) {
				System.out.println(wordList.get(i).getWord()+" : "+wordList.get(i).getMean());
				break;
			} else {
				System.out.println("일치하는 단어가 없습니다.");
			}
		}
	}
	
	// 단어 삭제 메서드
	public void deleteWord(String word) {
		if(wordList.size()==0) {System.out.println("저장된 단어가 없습니다."); return;} 
		for(int i=0; i<wordList.size(); i++) {
			if(wordList.get(i).getWord().equals(word)) {
				wordList.remove(i);
				break;
			} else {
				System.out.println("일치하는 단어가 없습니다.");
			}
		}
	}
	
	// 단어 수정 메서드
	public void rename(String word, String mean) {
		if(wordList.size()==0) {System.out.println("저장된 단어가 없습니다."); return;}
		for(int i=0; i<wordList.size(); i++) {
			if(wordList.get(i).getWord().equals(word)) {
				wordList.get(i).setWord(word);
				wordList.get(i).setMean(mean);
				break;
			} else {
				System.out.println("일치하는 단어가 없습니다.");
			}
		}
	}
	
	
	// 단어장 출력 메서드
	public void printWordList() {
		if(wordList.size()==0) {System.out.println("저장된 단어가 없습니다."); return;}
		System.out.println("단어장에 저장된 단어를 출력합니다.");
		for(int i=0; i<wordList.size(); i++) {
			System.out.println((i+1) + ". " + wordList.get(i).getWord()+" : "+wordList.get(i).getMean());
		}
	}
	
//	public void filePrint() throws IOException {
//		try {
//			FileWriter fw = new FileWriter("word.txt");
//			for(int i=0; i<wordList.size(); i++) {
//				fw.write("단어 : "+wordList.get(i).getWord() + ", 의미 : "+ wordList.get(i).getMean() + "\r\n");
//			}
//			
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		BufferedReader br = 
//				new BufferedReader(new FileReader("word.txt"));
//		while(true) {
//			String line = br.readLine(); // 한 라인 읽어오기
//			if(line==null) { // 더이상 읽을 라인이 없음
//				break;
//			}
//			System.out.println(line);
//		}
//		br.close();
//	}
	
	public void filePrint() throws IOException {
		// 파일로 출력
		FileWriter fw = new FileWriter("word.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		StringBuffer sb = new StringBuffer();
		
		String data = "";
		int cnt = 0;
		
		while(cnt < wordList.size()) {
			sb.append(wordList.get(cnt).toString());
			sb.append("\r\n");
			cnt++;
		}
		data = sb.toString();
		System.out.println(data);
		fw.write(data);
		fw.close();
	}
	
}
