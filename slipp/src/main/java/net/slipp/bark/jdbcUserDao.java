package net.slipp.bark;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcUserDao extends JdbcDaoSupport implements UserDao{

	private static final Logger log = LoggerFactory.getLogger(jdbcUserDao.class);
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("slipp.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		log.info("database initialized success!");
	}

	/* (non-Javadoc)
	 * @see net.slipp.dao.users.IUserDao#findById(java.lang.String)
	 */
	@Override
	public User findById(String userId) {
		
		String sql = "select * from users where userId = ?";
		
		RowMapper<User> rowMapper = new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
							rs.getString("userId"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("email"));
			}
		};
		
		try{
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see net.slipp.dao.users.IUserDao#create(net.slipp.domain.users.User)
	 */
	@Override
	public void create(User user){
		String sql = "insert into users values(?, ?, ?, ?)";
		getJdbcTemplate().update(sql,
						user.getUserId(),
						user.getPassword(),
						user.getName(),
						user.getEmail());
	}

	/* (non-Javadoc)
	 * @see net.slipp.dao.users.IUserDao#modify(net.slipp.domain.users.User)
	 */
	@Override
	public void modify(User user) {
		String sql = "update users set password=?, name=?, email=? where userId=?";
		getJdbcTemplate().update(sql,
						user.getPassword(),
						user.getName(),
						user.getEmail(),
						user.getUserId());		
	}
}
