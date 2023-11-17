package word;

public class Word {
	private String word;
	private String mean;
	
	
	public Word() {}
	
	public Word(String word, String mean) {
		this.word = word;
		this.mean = mean;
	}
	
	
	
	@Override
	public String toString() {
		return "단어 :" + word + ", 의미:" + mean;
	}

	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getMean() {
		return mean;
	}
	
	public void setMean(String mean) {
		this.mean = mean;
	}
	
}
