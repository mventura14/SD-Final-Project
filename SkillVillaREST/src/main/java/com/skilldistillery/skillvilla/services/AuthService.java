package com.skilldistillery.skillvilla.services;


import com.skilldistillery.skillvilla.entities.User;

public interface AuthService {
	
	public User register(User user);

	public User getUserByUsername(String username);
}
