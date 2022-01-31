package com.zee.zee5app.repository.impl;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

public class UserRepositoryImpl implements UserRepository {
	
	DBUtils dbUtils = DBUtils.getInstance();
	private static UserRepository repository;
	static private LoginRepositoryImpl loginRepository = null;
	
	private UserRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
		loginRepository = LoginRepositoryImpl.getInstance();
		dbUtils = DBUtils.getInstance();
	}
	
	
	
	public static UserRepository getInstance() throws IOException {
		if(repository==null) {
			repository = new UserRepositoryImpl();
		}
		return repository;
	}

	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;

		String insertStatement = "insert into register"
				+ " (regId,firstname,lastname,email,contactnumber,password)"
				+ " values(?,?,?,?,?,?)";

		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,register.getId());
			preparedStatement.setString(2, register.getFirstName());
			preparedStatement.setString(3, register.getLastName());
			preparedStatement.setString(4, register.getEmail());
			preparedStatement.setBigDecimal(5, register.getContactNumber());
			//preparedStatement.setString(6, register.getPassword());
			String encryptPassword = PasswordUtils.generateSecurePassword(register.getPassword(),
					PasswordUtils.getSalt(30));
			preparedStatement.setString(6, encryptPassword);
			int result =preparedStatement.executeUpdate();

			
			if (result > 0) {
				Login login = new Login(register.getEmail(), encryptPassword, register.getId(), ROLE.ROLE_USER );
				String status = loginRepository.addCredentials(login);
				if (status.equals("success")) {
					return "success";
				} else {
					connection.rollback();
					return "fail";
				}
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
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}
		
		
	

	@Override
	public String updateUserById(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "select * from register where regId=?";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			
			resultSet =  preparedStatement.executeQuery();

			if (resultSet.next()) {

				Register register = new Register();
				register.setId(resultSet.getString("regId"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactNumber(resultSet.getBigDecimal("contactnumber"));
				return Optional.of(register);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Register>> getAllUsersList() throws InvalidNameException, IdNotFoundException,
			InvalidIdLengthException

	{
		List<Register> registers = new ArrayList<>();
		Connection connection = dbUtils.getConnection();

		String getQuery = "SELECT * FROM register";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Register register = new Register();
				register.setId(result.getString("regId"));
				register.setFirstName(result.getString("firstname"));
				register.setLastName(result.getString("lastname"));
				register.setEmail(result.getString("email"));
				register.setPassword(result.getString("password"));
				register.setContactNumber(result.getBigDecimal("contactnumber"));
				registers.add(register);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}

		return Optional.ofNullable(registers);
	}
	
	@Override
	public Register[] getAllUsers() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{

		Optional<List<Register>> registers = this.getAllUsersList();

		//if (registers.isPresent()) {
	
		return registers.get().toArray(new Register[registers.get().size()]);
		//}
			//return null;
	}



	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		Connection connection = dbUtils.getConnection();
		String delQuery = "DELETE FROM register where regId=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
				throw new IdNotFoundException("Invalid Id");
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
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}



	@Override
	public List<Register> getAllUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public List<Register> getAllUserDetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}




//	@Override
//	public Optional<List<Register>> getAllUsersList() throws InvalidNameException, InvalidIdLengthException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String updateUserById(String id, Register register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

		
}