package JDBC;

import java.util.List;

public class ProductServiceImpl implements Service {
	// ServiceImpl <=> DAO
	
	private DAO dao;  // interface로 생성 -> DAOImpl로 구현
	
	public ProductServiceImpl() {
		dao = new ProductDAOImpl();  // 구현체
	}

	// 구현
	@Override
	public int register(Product p) {
		// 상품등록 메서드
		System.out.println("register_service success!!");
		// dao에서 사용되는 메서드는 실제 DB에서 사용하는 명령어와 비슷하게 메서드를 작성
		return dao.insert(p);
	}

	@Override
	public List<Product> list() {
		// 리스트 출력 메서드
		System.out.println("list_service success!!");
		return dao.selectList();
	}

	@Override
	public Product detail(int pno) {
		// TODO Auto-generated method stub
		System.out.println("detail_service success!!");
		return dao.selectOne(pno);
	}
	
	
}
