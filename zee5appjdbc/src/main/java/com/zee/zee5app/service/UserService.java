package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;

public interface UserService {

	public String addUser(Register register);
	//public String updateUser(String id, Register register);
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Register[] getAllUsers() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	public String deleteUserById(String id) throws IdNotFoundException;
	public List<Register> getAllUserDetails();
	Optional<List<Register>> getAllUsersList()
			throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	String updateUserById(String id, Register register) throws IdNotFoundException;
}