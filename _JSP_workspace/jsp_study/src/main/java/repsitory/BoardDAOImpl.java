package repsitory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	
	// DB 연결
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>>>> insert check 3");
		// 실제 DB에 저장 => mybatis / mapper
		// sql.insert(mapperNamespace.id 로 인식)
		// id는 우리가 별도로 줄 수 있음?
		int isOk = sql.insert("BoardMapper.add", bvo);
		// insert, update, delete 시 DB가 변경되는 구문
		if(isOk>0) {
			sql.commit();
			// commit은 반드시 써줘야함! 그래야 DB에 반영
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList(PagingVO pgvo) {
		log.info(">>>>> list check 3");
		return sql.selectList("BoardMapper.list", pgvo);
	}

	@Override
	public int readCountUpdate(int bno) {
		log.info(">>>>> readCountUpdate check");
		int isOk = sql.update("BoardMapper.read", bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>>> getDetail check  3");
		return sql.selectOne("BoardMapper.detail", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info(">>>>> modify check  3");
		int isOk = sql.update("BoardMapper.up", bvo);
		if(isOk > 0) {sql.commit();}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info(">>>>> remove check  3");
		int isOk = sql.delete("BoardMapper.del", bno);
		if(isOk > 0) {sql.commit();}
		return isOk;
	}

	@Override
	public int boardCount(PagingVO pgvo) {
		log.info(">>>>> remove check  3");
		return sql.selectOne("BoardMapper.total", pgvo);
		// count(*) 을 하면 DB 성능저하가 초래될 수도 있고, 
		// title이 null이거나 할때 값이 포함되지 않을수도 있음
	}

	@Override
	public String selectImage(int bno) {
		return sql.selectOne("BoardMapper.img",bno);
	}
	
}
