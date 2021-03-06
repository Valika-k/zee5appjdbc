package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

public class LoginRepositoryImpl implements LoginRepository {

	DBUtils dbutils = null;
	private static LoginRepositoryImpl loginRepository = null;

	private LoginRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
	}

	public static LoginRepositoryImpl getInstance() throws IOException {
		if (loginRepository == null)
			loginRepository = new LoginRepositoryImpl();
		return loginRepository;
	}

	@Override
	public String addCredentials(Login login) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "insert into login (username, password, regId, role)" + "values(?,?,?,?)";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(insertQuery);
			prepStatement.setString(1, login.getUserName());
			prepStatement.setString(2, login.getPassword());
			prepStatement.setString(3, login.getRegId());
			prepStatement.setString(4,  login.getRole().toString());
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
				return "fail";
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public String deleteCredentials(String userName) {
		Connection connection = dbutils.getConnection();
		String delQuery = "DELETE FROM login where username=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, userName);
			int result = prepStatement.executeUpdate();
			if (result > 0)
				return "success";
			else
				return "fail";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public String changePassword(String userName, String password) {

		return null;
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		Connection connection = dbutils.getConnection();
		PreparedStatement prepStatement;
		
		String updateStatement ="UPDATE login SET role=? where username=?";
		try {
			prepStatement = connection.prepareStatement(updateStatement);
			prepStatement.setString(1, role.toString());
			prepStatement.setString(2, userName);
			int result = prepStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "Success";
			}else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
				
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			return "fail";
		}
		finally {
			dbutils.closeConnection(connection);
		}
		
	}

}