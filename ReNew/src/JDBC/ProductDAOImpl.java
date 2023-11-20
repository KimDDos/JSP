package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements DAO {

	// DB와 연결
	// DB와 실제 연결하는 구문 필요
	
	// ↓ DB와 연결하는 역할
	private Connection conn;
	// ↓ sql구문을 실행시키는 기능을 갖는 객체
	private PreparedStatement pst;
	// ↓ 쿼리문 저장
	private String query="";
	
	public ProductDAOImpl() {
		// DB 연결정보를 설정하는 class 싱글톤으로 생성
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		conn = dbc.getConnection();
	}

	
	// sql 구문 처리
	// executeQuery() : select 구문에서 사용 ResultSet이 리턴
	// executeUpdate() : insert, update, delete 별도의 리턴이 없고, 0 / 1 로만 결과값 
	
	
	@Override
	public int insert(Product p) {
		// DB와 직접 연결
		System.out.println("insert_DAO success!!");
		query="insert into product(pname, price, madeby) values(?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			
			// ? 값을 setting
			// set은 0번지부터가 아니라 1번부터 시작임!!
			pst.setString(1, p.getPname());
			pst.setInt(2, p.getPrice());
			pst.setString(3, p.getMadeby());
			
			return pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insert error!");
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<Product> selectList() {
		// 
		System.out.println("insert_DAO success!!");
		query="select * from product order by pno desc";
		List<Product> list = new ArrayList<Product>();
		
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int pno = rs.getInt("pno");
				list.add(new Product(pno, rs.getString("pname"), rs.getInt("price")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("list error!");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Product selectOne(int pno) {
		// TODO Auto-generated method stub
		System.out.println("detail_DAO success!!");
		query="select * from product where pno = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return new Product(rs.getInt("pno"), rs.getString("pname"), rs.getInt("price"), rs.getString("regdate"), rs.getString("madeby") );
			}
		} catch (SQLException e) {
			System.out.println("detail error!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
