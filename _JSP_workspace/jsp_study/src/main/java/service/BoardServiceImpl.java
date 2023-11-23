package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repsitory.BoardDAO;
import repsitory.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDAO bdao;  // interface로 생성
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}
	
}
