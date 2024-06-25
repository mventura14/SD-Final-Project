package com.skilldistillery.skillvilla.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.entities.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User show(int id) {
		return userRepo.findById(id).get();
	}

	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(String username, int userId, User user) {
		Optional<User> userOptional = userRepo.findById(userId);
		User managedUser = null;

		if (userRepo.existsByIdAndUsername(userId, username)) {
			if (userOptional.isPresent()) {
				managedUser = userOptional.get();
				
				if(user.getUsername() != null && !user.getUsername().isBlank()) {managedUser.setUsername(user.getUsername());}
				if(user.getPassword() != null && !user.getPassword().isBlank()) {managedUser.setPassword(user.getPassword());}
				if(user.getRole() 	  != null && !user.getRole().isBlank()) 	{managedUser.setRole(user.getRole());}
				if(user.getEmail()    != null && !user.getEmail().isBlank()) 	{managedUser.setEmail(user.getEmail());}
				if(user.getFirstName()!= null && !user.getFirstName().isBlank()){managedUser.setFirstName(user.getFirstName());}
				if(user.getLastName() != null && !user.getLastName().isBlank()) {managedUser.setLastName(user.getLastName());}
				if(user.getLocation() != null) 									{managedUser.setLocation(user.getLocation());}
				if(user.getImageUrl() != null && !user.getImageUrl().isBlank()) {managedUser.setImageUrl(user.getImageUrl());}
				if(user.getBio() != null && !user.getBio().isBlank()) {managedUser.setBio(user.getBio());}
				managedUser.setEnabled(user.isEnabled());
				userRepo.saveAndFlush(managedUser);
			}
		}

		return managedUser;
	}

//	User updatedUser = userRepo.findById(
//			id);updatedUser.setUsername(user.getUsername());updatedUser.setPassword(user.getPassword());updatedUser.setEmail(user.getEmail());updatedUser.setCreatedAt(user.getCreatedAt());updatedUser.setUpdatedAt(user.getUpdatedAt());updatedUser.setRole(user.getRole());updatedUser.setEnabled(user.isEnabled());return userRepo.saveAndFlush(updatedUser);
//	}

	@Override
	public void delete(int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
		}
	}

}
