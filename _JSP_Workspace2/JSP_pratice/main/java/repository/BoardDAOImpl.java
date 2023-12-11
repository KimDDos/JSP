package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private SqlSession sql; 
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk = sql.insert("BoardMapper.reg",bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> selectAll(PagingVO pgvo) {
		log.info("selectAll check 3");
		return sql.selectList("BoardMapper.All", pgvo);
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("selectOne check 3");
		return sql.selectOne("BoardMapper.detail",bno);
	}

	@Override
	public int readCount(int bno) {
		log.info("readCount check 3");
		int isOk = sql.update("BoardMapper.read", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("modify check 3");
		int isOk = sql.update("BoardMapper.modify", bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("delete check 3");
		int isOk = sql.delete("BoardMapper.del", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> getMylist(String id) {
		log.info("getMylist check 3");
		return sql.selectList("BoardMapper.myboard",id);
	}
	
	@Override
	public int getTotalCount(PagingVO pgvo) {
		return sql.selectOne("BoardMapper.tot", pgvo);
	}

	@Override
	public String searchImage(int bno) {
		return sql.selectOne("BoardMapper.img",bno);
	}
	
	
}
