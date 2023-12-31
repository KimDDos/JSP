package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import repsitory.CommentDAO;
import repsitory.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(CommentVO cvo) {
		log.info("post check 2");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("list check 2");
		return cdao.selectAll(bno);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int commentDel(int bno) {
		// TODO Auto-generated method stub
		return cdao.cmtDelete(bno);
	}
	
	
	
}
