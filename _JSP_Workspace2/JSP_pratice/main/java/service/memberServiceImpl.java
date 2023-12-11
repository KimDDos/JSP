package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class memberServiceImpl implements memberService {

	private static final Logger log = LoggerFactory.getLogger(memberServiceImpl.class);
	
	private MemberDAO mdao;
	
	public memberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int signup(MemberVO mvo) {
		log.info("signup check 2");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.selectId(mvo);
	}

	@Override
	public List<MemberVO> getList() {
		log.info("getList check 2");
		return mdao.selectAll();
	}

	@Override
	public MemberVO detail(String id) {
		log.info("detail check 2");
		return mdao.selectOne(id);
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify check 2");
		return mdao.update(mvo);
	}

	@Override
	public int remove(String id) {
		log.info("remove check 2");
		return mdao.remove(id);
	}

	@Override
	public int lastloginupdate(String id) {
		log.info("lastlogin update check 2");
		return mdao.loginupdate(id);
	}
}
