package com.skilldistillery.skillvilla.services;

import java.util.List;

import com.skilldistillery.skillvilla.entities.Community;

public interface CommunityService {
	
	List<Community> findAll();
	
	Community show(int id);

	void delete(int id);

	Community create(String name, Community community);

	Community update(String username, int communityId, Community community);

	

	

}
