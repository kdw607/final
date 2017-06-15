package net.slipp.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.*;
import net.slipp.dao.users.UserDaoTest;
import net.slipp.domain.users.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
	
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setup() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void gettingStarted() {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById", "javajigi");
			log.debug("User : {}", user);
		}
	}
	
	@Test
	public void insert() throws Exception{
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("kdw607", "pass1234", "±Ç´ë¿ø", "kdw607@naver.com");
			session.insert("UserMapper.create", user);
			User dbuser = session.selectOne("UserMapper.findById", user.getUserId());
			assertThat(dbuser, is(user));
		}
	}
}
