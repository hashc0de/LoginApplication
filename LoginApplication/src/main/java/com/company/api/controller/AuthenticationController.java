package com.company.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.dto.LoginRequestDTO;
import com.company.api.dto.LoginResponseDTO;
import com.company.api.exception.NotAValidEmailAddressException;
import com.company.api.exception.UnsuccessfulLoginException;
import com.company.api.mapper.LoginResponseMapper;
import com.company.api.service.LoginResponse;
import com.company.api.service.LoginService;
import com.company.api.validator.EmailValidator;

@RestController
public class AuthenticationController {

	@Autowired
	private LoginService service;

	@Autowired
	private EmailValidator validator;
	
	@Autowired
	private LoginResponseMapper mapper;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseDTO login(@Valid @RequestBody final LoginRequestDTO request) throws NotAValidEmailAddressException {
		String email = request.getEmail();
		if (!validator.isValid(email)) {
			throw new NotAValidEmailAddressException(email);
		}
		LoginResponse response = service.login(request);
		checkResponse(response, email);
		return mapper.map(response);
		
	}

	private void checkResponse(LoginResponse response, String email) {
		if(response == null){
			throw new UnsuccessfulLoginException(
					"email with password combination not found. Unsuccessful login for e-mail address: %s.",
					"Unsuccessful login.", email);
		}
		
	}
}
