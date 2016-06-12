package com.company.api.mapper;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.api.dto.LoginResponseDTO;
import com.company.api.helper.TokenBuilder;
import com.company.api.service.LoginResponse;

@Component
public class LoginResponseMapper {

	@Autowired
	private TokenBuilder tokenBuilder;

	public LoginResponseDTO map(LoginResponse response) {
		LoginResponseDTO responseDto = new LoginResponseDTO();
		String token = tokenBuilder.buildToken(response.getIssuedAt(), response.getExpiration(), response.getEmail(), response.getName());
		responseDto.setToken(token);
		responseDto.setExpires(response.getExpiration(TimeUnit.SECONDS));

		return responseDto;
	}

}
