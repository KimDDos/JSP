package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO selectId(MemberVO mvo);

	List<MemberVO> selectAll();

	MemberVO selectOne(String id);

	int update(MemberVO mvo);

	int remove(String id);

	int loginupdate(String id);

}
