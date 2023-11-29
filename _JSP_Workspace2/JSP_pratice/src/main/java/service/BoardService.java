package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getlist();

	BoardVO detail(int bno);

	int readcount(int bno);

	int modify(BoardVO bvo);

	int remove(int bno);

}
