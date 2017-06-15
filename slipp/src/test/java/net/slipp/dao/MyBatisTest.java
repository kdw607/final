package net.slipp.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import net.slipp.dao.users.UserDaoTest;
import net.slipp.domain.users.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

	@Test
	public void gettingStarted() throws Exception {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById", "javajigi");
			log.debug("User : {}", user);
		} finally {
		  session.close();
		}
	}
}
