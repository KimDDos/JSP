package repsitory;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectAll(int bno);

	int delete(int cno);
 
	
}
