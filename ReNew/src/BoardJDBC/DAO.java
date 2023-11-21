package BoardJDBC;

import java.util.List;

public interface DAO {

	int insert(BoardVO bo);

	List<BoardVO> selectAll();

	BoardVO selectOne(int bno);

	int update(BoardVO bo);

	int delete(int bno);

	void raedcountUpdate(int bno);

}
