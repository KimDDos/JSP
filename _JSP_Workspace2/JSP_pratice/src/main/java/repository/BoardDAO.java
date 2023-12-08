package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectAll(PagingVO pgvo);

	BoardVO selectOne(int bno);

	int readCount(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	List<BoardVO> getMylist(String id);

	int getTotalCount(PagingVO pgvo);

}
