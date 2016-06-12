package com.company.api.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.company.api.dto.LoginRequestDTO;
import com.company.api.helper.ApplicationConstants;
import com.company.api.helper.CachedUsers;

public class LoginService {

	Map<String, String[]> users = CachedUsers.getUsers();

	public LoginResponse login(LoginRequestDTO request) {
		if (users.containsKey(request.getEmail()) && users.get(request.getEmail())[0].equals(request.getPassword())) {
			return new LoginResponse(new Date(), ApplicationConstants.EXPIRATION_IN_MINUTES, TimeUnit.MINUTES, request.getEmail(),
					users.get(request.getEmail())[1]);
		}
		return null;
	}

	public boolean isCorrectInformation(String email, String name){
		if (users.containsKey(email) && users.get(email)[1].equals(name)) {
			return true;
		}
		return false;
	}
}
