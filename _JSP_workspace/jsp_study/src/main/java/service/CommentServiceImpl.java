package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repsitory.CommentDAO;
import repsitory.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}
	
	
	
}
