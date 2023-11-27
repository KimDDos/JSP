package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repsitory.MemberDAO;
import repsitory.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("register check 2!!");;
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2!!");
		return mdao.Login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("lastlogin check 2!!");
		return mdao.lastLogin(id);
	}
	
	
}
