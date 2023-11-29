package service;

import domain.MemberVO;

public interface memberService {

	int signup(MemberVO mvo);

	MemberVO login(MemberVO mvo);

}
