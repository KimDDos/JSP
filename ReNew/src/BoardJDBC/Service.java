package BoardJDBC;

import java.util.List;

public interface Service {

	int addBoard(BoardVO bo);

	List<BoardVO> list();

	BoardVO detail(int bno);

	int remove(int bno);

	int modify(BoardVO bo);

	void autoCount(int bno);
	
	
}
