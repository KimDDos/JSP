package service;

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
}
