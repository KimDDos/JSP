package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {
	
	private static final Logger log = LoggerFactory.getLogger(FileRemoveHandler.class);
	
	// 파일 이름과 경로를 받아 파일을 삭제하는 메서드
	// 리턴타입 int => 잘 삭제되었다면 1, 아니면 0
	public int deleteFile(String imageFileName, String savePath) {
		
		boolean isDel = false;
		log.info("delete file method >> {}", imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumbFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		
		// 파일이 존재해야 삭제 => exists() 값이 있으면 true, 없으면 false
		if(removeFile.exists() || removeThumbFile.exists()) {
			isDel = removeFile.delete();
			log.info(">>>>>> fileRemove : " + (isDel ? "Ok" : "Fail"));
			// file을 삭제하는 메서드
			if(isDel) {
				isDel = removeThumbFile.delete();
				log.info(">>>>>> ThumbFileremove : " + (isDel ? "Ok" : "Fail"));
			}
		}
		
		log.info(">>>> remove Success!! <<<<");
		
		return isDel ? 1 : 0;
	}
}
