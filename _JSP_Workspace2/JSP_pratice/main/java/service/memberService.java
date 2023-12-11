package service;

import java.util.List;

import domain.MemberVO;

public interface memberService {

	int signup(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	List<MemberVO> getList();

	MemberVO detail(String id);

	int modify(MemberVO mvo);

	int remove(String id);

	int lastloginupdate(String id);

}
