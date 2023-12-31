package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
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
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">>>>>> list check 2");
		return bdao.selectList(pgvo);
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

	@Override
	public int remove(int bno) {
		log.info(" >>>> remove check 2 ");
		return bdao.delete(bno);
	}

	@Override
	public int boardCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.boardCount(pgvo);
	}

	@Override
	public String searchImage(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectImage(bno);
	}
	
}
