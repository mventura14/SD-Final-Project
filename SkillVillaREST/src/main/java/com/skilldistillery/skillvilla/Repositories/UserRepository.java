package com.skilldistillery.skillvilla.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillvilla.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
	
	@Override
	List <User> findAll();

	boolean existsByIdAndUsername(int userId, String username);
	
}
