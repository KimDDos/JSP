package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getlist(PagingVO pgvo);

	BoardVO detail(int bno);

	int readcount(int bno);

	int modify(BoardVO bvo);

	int remove(int bno);

	List<BoardVO> getMylist(String id);

	int boardCount(PagingVO pgvo);

	String searchImage(int bno);

}
