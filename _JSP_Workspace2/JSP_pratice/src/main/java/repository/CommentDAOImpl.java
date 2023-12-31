package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {

	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO cvo) {
		log.info("post check 3");
		isOk = sql.insert("CommentMapper.add", cvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> getlist(int bno) {
		log.info("comment List check 3!");
		return sql.selectList("CommentMapper.list", bno);
	}

	@Override
	public int delete(int cno) {
		log.info("remove check 3");
		isOk = sql.delete("CommentMapper.del",cno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("modify check 3");
		isOk = sql.update("CommentMapper.up",cvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}
	
}
