package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseBuilder {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseBuilder.class);
	private static SqlSessionFactory factory;
	private static final String Config = "orm/MybatisConfig.xml";
	
	// 초기화 블록
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(Config));
		} catch (Exception e) {
			log.info("DatabaseBuilder Error!");
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
}
