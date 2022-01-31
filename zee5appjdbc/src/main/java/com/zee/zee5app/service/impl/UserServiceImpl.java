package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;

import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserServiceImpl()  throws IOException {
		// TODO Auto-generated constructor stub
	}
	
	private static UserService service;
	
	public static UserService getInstance() throws IOException {
		
		if(service==null) {
			service = new UserServiceImpl();
		}
		
		return service;
	}
	
	//UserRepository userRepository ;

	UserRepository userRepository  =  UserRepositoryImpl.getInstance();
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return userRepository.addUser(register);
	}

	@Override
	public String updateUserById(String id, Register register) throws IdNotFoundException {
		return this.userRepository.updateUserById(id, register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return userRepository.getUserById(id);
	}
	
	@Override
	public Optional<List<Register>> getAllUsersList() throws InvalidNameException, IdNotFoundException,
			InvalidIdLengthException

	{
		return this.userRepository.getAllUsersList();
	}
	
	@Override
	public Register[] getAllUsers() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{
return this.userRepository.getAllUsers();
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.deleteUserById(id);
	}

	@Override
	public List<Register> getAllUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<Register> getAllUserDetails() {
//		// TODO Auto-generated method stub
//		return userRepository.getAllUserDetails();
//	}

}