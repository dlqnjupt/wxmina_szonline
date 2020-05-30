package service;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

public class LoginService {

	public User login(String username, String password) throws SQLException {
		
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}


}
