package com.skilldistillery.skillvilla.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.entities.User;

@Service
public class AuthServiceImpl implements AuthService {

	private PasswordEncoder encoder;
	private UserRepository userRepo;

	public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepo) {
		super();
		this.encoder = encoder;
		this.userRepo = userRepo;
	}

	@Override
	public User register(User user) {
		String encryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setEnabled(true);
		user.setRole("standard");
		userRepo.saveAndFlush(user);
		return user;

	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
