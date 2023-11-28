package repsitory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// sql Session 객체 
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("rigister check 3");;
		int isOk = sql.insert("MemberMapper.reg", mvo);
		if(isOk > 0) {sql.commit();}
		return isOk;
	}

	@Override
	public MemberVO Login(MemberVO mvo) {
		log.info("isLogin check 3");
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("lastLogin check 3");
		int isOk = sql.update("MemberMapper.last" ,id);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> selectAll() {
		log.info("list check 3");
		return sql.selectList("MemberMapper.selectAll");
	}

	@Override
	public int selectOne(MemberVO mvo) {
		log.info("detail check 3");
		return sql.selectOne("MemberMapper.selectOne",mvo);
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("update check 3");
		int isOk = sql.update("MemberMapper.modify", mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info("delete check 3");
		int isOk = sql.delete("MemberMapper.del",id);
		if(isOk > 0) sql.commit();
		return isOk;
	}
	
	// 메서드 처리
	
}
