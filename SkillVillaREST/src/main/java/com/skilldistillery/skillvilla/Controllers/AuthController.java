package com.skilldistillery.skillvilla.Controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.User;
import com.skilldistillery.skillvilla.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		if (user == null) {
			res.setStatus(400);
			return null;
		}
		user = authService.register(user);
		return user;
	}

	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			
		}
		return authService.getUserByUsername(principal.getName());
	}

}