package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import repsitory.BoardDAO;
import repsitory.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDAO bdao;  // interface로 생성
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info(">>>> insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>>>>> list check 2");
		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(" >>>> detail check 2 ");
		// detail 체크시 readCount +1 
		int isOk = bdao.readCountUpdate(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info(" >>>> modify check 2 ");
		return bdao.update(bvo);
	}
	
}
