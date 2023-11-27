package repsitory;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO Login(MemberVO mvo);

	int lastLogin(String id);

}
