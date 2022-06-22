package com.secure.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secure.jwt.entity.AuthRequest;
import com.secure.jwt.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String Welcome() {
		return "welcome to spring security sesssion";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())

			);
		} catch (Exception ex) {
			throw new Exception("Invalid username or password");
		}

		return jwtUtil.generateToken(authRequest.getUserName());

	}
}
