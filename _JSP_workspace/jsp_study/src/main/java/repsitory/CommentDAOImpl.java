package repsitory;

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
		isOk = sql.insert("CommentMapper.post", cvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> selectAll(int bno) {
		return sql.selectList("CommentMapper.list",bno);
	}

	@Override
	public int delete(int cno) {
		isOk = sql.delete("CommentMapper.del", cno);
		if(isOk > 0) sql.commit();
		return isOk;
	}
	
	
}
