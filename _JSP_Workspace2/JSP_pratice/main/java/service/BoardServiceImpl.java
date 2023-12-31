package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getlist(PagingVO pgvo) {
		log.info("list check 1");
		return bdao.selectAll(pgvo);
	}

	@Override
	public BoardVO detail(int bno) {
		log.info("detail check 2");
		return bdao.selectOne(bno);
	}

	@Override
	public int readcount(int bno) {
		log.info("readCount check 2");
		return bdao.readCount(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info("modify check 2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check 2");
		return bdao.delete(bno);
	}

	@Override
	public List<BoardVO> getMylist(String id) {
		log.info("getMylist check 2");
		return bdao.getMylist(id);
	}

	@Override
	public int boardCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public String searchImage(int bno) {
		// TODO Auto-generated method stub
		return bdao.searchImage(bno);
	}
	
}
