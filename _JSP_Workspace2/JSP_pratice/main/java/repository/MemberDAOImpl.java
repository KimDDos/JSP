package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("insert check 3");
		int isOk = sql.insert("MemberMapper.join",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO selectId(MemberVO mvo) {
		log.info("selectId check 3");
		return sql.selectOne("MemberMapper.login",mvo);
	}

	@Override
	public List<MemberVO> selectAll() {
		log.info("selectAll check 3");
		return sql.selectList("MemberMapper.list");
	}

	@Override
	public MemberVO selectOne(String id) {
		log.info("detail check 3");
		return sql.selectOne("MemberMapper.detail",id);
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("modify check 3");
		int isOk = sql.update("MemberMapper.up",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int remove(String id) {
		log.info("remove check 3");
		int isOk = sql.delete("MemberMapper.del", id);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int loginupdate(String id) {
		log.info("lastlogin update check 3");
		int isOk = sql.update("MemberMapper.log",id);
		if(isOk > 0) sql.commit();
		return isOk;
	}
	
	
	
}
