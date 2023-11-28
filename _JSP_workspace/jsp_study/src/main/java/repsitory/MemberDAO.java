package repsitory;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO Login(MemberVO mvo);

	int lastLogin(String id);

	List<MemberVO> selectAll();

	int selectOne(MemberVO mvo);

	int update(MemberVO mvo);

	int delete(String id);

}
