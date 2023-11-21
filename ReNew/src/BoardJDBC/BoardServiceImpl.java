package BoardJDBC;

import java.util.List;

public class BoardServiceImpl implements Service {

	private DAO dao;
	
	
	public BoardServiceImpl() {
		dao = new BoardDAOImpl();
	}


	@Override
	public int addBoard(BoardVO bo) {
		// 게시글 추가 메서드
		System.out.println("AddBoard_Service success!");
		return dao.insert(bo);
	}


	@Override
	public List<BoardVO> list() {
		// 전체 리스트 출력 메서드
		System.out.println("showBoardList_Service success!");
		return dao.selectAll();
	}


	@Override
	public BoardVO detail(int bno) {
		// 게시글 상세보기
		System.out.println("showOneBoard_Service success!");
		return dao.selectOne(bno);
	}


	@Override
	public int modify(BoardVO bo) {
		// 게시글 수정
		System.out.println("modifyBoard_Service success!");
		return dao.update(bo);
	}


	@Override
	public int remove(int bno) {
		// 게시글 삭제
		System.out.println("removeBoard_Service success!");
		return dao.delete(bno);
	}


	@Override
	public void autoCount(int bno) {
		// readcount 자동 증가 메서드
		System.out.println("ReadCount_Service success!");
		dao.raedcountUpdate(bno);
	}


	
}
