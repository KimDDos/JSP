package repository;

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
	
	
	
}
