package com.company.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.dto.AccessToken;
import com.company.api.dto.HomePageResponseDTO;
import com.company.api.exception.BadRequestException;
import com.company.api.service.LoginService;

@RestController
public class HomeController {

	@Autowired
	private LoginService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public HomePageResponseDTO getWelcomeMessage(@RequestHeader(required = true) AccessToken token) {
		if (service.isCorrectInformation(token.getEmail(), token.getName())) {
			HomePageResponseDTO response = new HomePageResponseDTO();
			response.setMessage(token.getName());
			return response;
		}
	throw new BadRequestException("Incorrect name and email combination");
	}

}
