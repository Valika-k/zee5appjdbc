package com.zee.zee5app.service.impl;

import java.io.IOException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.impl.LoginRepositoryImpl;
import com.zee.zee5app.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public class getInstance implements LoginService {

		@Override
		public String addCredentials(Login login) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String deleteCredentials(String userName) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String changePassword(String userName, String password) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String changeRole(String userName, ROLE role) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private static LoginServiceImpl loginService = null;

	private static LoginRepositoryImpl loginRepository = null;

	public LoginServiceImpl() throws IOException {
		loginRepository = LoginRepositoryImpl.getInstance();
	}

	public static LoginServiceImpl getInstance() throws IOException {
		if (loginService == null)
			loginService = new LoginServiceImpl();
		return loginService;
	}

	@Override
	public String addCredentials(Login login) {
		return this.loginRepository.addCredentials(login);
	}

	@Override
	public String deleteCredentials(String userName) {
		return this.loginRepository.deleteCredentials(userName);
	}

	@Override
	public String changePassword(String userName, String password) {
		return this.loginRepository.changePassword(userName, password);
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		return loginRepository.changeRole(userName, role);
	}

}