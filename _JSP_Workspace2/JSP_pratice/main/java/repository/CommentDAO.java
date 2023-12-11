package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getlist(int bno);

	int delete(int cno);

	int update(CommentVO cvo);

	int cmtDel(int bno);

}
