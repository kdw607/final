package net.slipp.dao.users;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.User;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UserDaoTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("javajigi");
		log.debug("User : {}", user);
	}
	
	@Test
	@Transactional
	public void create() throws Exception {
		User user = new User("sanjiginim", "password", "������", "sanjigi@gmail.com");
		userDao.create(user);
		User dbUser = userDao.findById(user.getUserId());
		assertThat(dbUser, is(user));
	}
}