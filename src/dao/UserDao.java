package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.User;
import utils.DataSourceUtils;

public class UserDao {

	public User login(String username, String password) throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from user_mina where user_name = ? and user_password = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
		}
}
