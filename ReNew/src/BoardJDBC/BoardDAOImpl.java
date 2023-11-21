package BoardJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements DAO {
	
	// DB 연결
	
	private Connection conn;
	private PreparedStatement pst;
	private String query="";
	private ResultSet rs;
	
	public BoardDAOImpl() {
		// 여기서 싱글톤으로 DB 연결 구문 작성
		// 데이터베이스 정보 객체 생성
		DataBaseConnection dbc = DataBaseConnection.getInstance();
		conn = dbc.getConnection();
	}

	@Override
	public int insert(BoardVO bo) {
		System.out.println("insert_DAO success!");
		// title, writer, content
		query = "insert into board(title, writer, content) values(?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			
			pst.setString(1, bo.getTitle());
			pst.setString(2, bo.getWriter());
			pst.setString(3, bo.getContent());
			
			return pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert error!");
		}
		
		return 0;
	}

	@Override
	public List<BoardVO> selectAll() {
		// 등록 게시글 전체 요약 출력
		// 글  목록보기용 : bno, title, writer, moddate
		System.out.println("SelectAll_DAO success!");
		query="select * from board order by bno desc";
		List<BoardVO> selectAll = new ArrayList<BoardVO>();
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String moddate = rs.getString("moddate");
				int readcount = rs.getInt("readcount");
				selectAll.add(new BoardVO(bno, title, writer, moddate, readcount));
			}
			return selectAll;
		} catch (SQLException e) {
			System.out.println("SelectAll error!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BoardVO selectOne(int bno) {
		// 게시글 상세보기 페이지임
		// connection은 한번 열고 닫아야 함
		
		System.out.println("SelectOne_DAO success!");
		query="select * from board where bno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, bno);
			rs = pst.executeQuery();
			if(rs.next()) {
				return new BoardVO(rs.getInt("bno"), rs.getString("title"), rs.getString("writer"), rs.getString("content"), rs.getString("regdate"), rs.getString("moddate"), rs.getInt("readcount"));
			}
		} catch (SQLException e) {
			System.out.println("selectOne error!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(BoardVO bo) {
		// 게시글 수정 메서드
		System.out.println("Update_DAO success!");
		query="update board set title=?, content=?, moddate=now() where bno=?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, bo.getTitle());
			pst.setString(2, bo.getContent());
			pst.setInt(3, bo.getBno());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Error!");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int bno) {
		// 게시글 삭제 메서드
		System.out.println("Delete_DAO success!");
		query="delete from board where bno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, bno);
			return pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete Error!");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void raedcountUpdate(int bno) {
		// readcount 자동 증가 메서드 
		System.out.println("auto_readcount_DAO success!");
		
		query="update board set readcount=readcount+1 where bno=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setInt(1, bno);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("readcount error!");
			e.printStackTrace();
		}
	}
	
	
	
}