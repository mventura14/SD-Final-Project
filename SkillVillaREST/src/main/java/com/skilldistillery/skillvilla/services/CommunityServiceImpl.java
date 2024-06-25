package com.skilldistillery.skillvilla.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.CommunityRepository;
import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.entities.Community;
import com.skilldistillery.skillvilla.entities.User;

@Service
public class CommunityServiceImpl implements CommunityService {

	private CommunityRepository commRepo;
	private UserRepository userRepo;

	public CommunityServiceImpl(CommunityRepository commRepo, UserRepository userRepo) {
		super();
		this.commRepo = commRepo;
		this.userRepo = userRepo;
	}

	@Override
	public List<Community> findAll() {
		return commRepo.findAll();
	}

	@Override
	public Community show(int id) {
		return commRepo.findById(id).get();
	}

	@Override
	public Community update(String username, int communityId, Community community) {

		Optional<Community> communityOptional = commRepo.findById(communityId);
		Community managedCommunity = null;
		
		//need to add user specific
		if (commRepo.existsById(communityId)) {
			
			if (communityOptional.isPresent()) {
				
				
				managedCommunity = communityOptional.get();
				
				if (community.getName() != null && !community.getName().isBlank()) {
					managedCommunity.setName(community.getName());};
				
				if (community.getDescription() != null && !community.getDescription().isBlank()) {
					managedCommunity.setDescription(community.getDescription());};
				
				if(community.getLocation() != null) {managedCommunity.setLocation(community.getLocation());}
				
				if(community.getImageUrl() != null) {managedCommunity.setImageUrl(community.getImageUrl());}
				
				if(community.getDiscordUrl() != null) {managedCommunity.setDiscordUrl(community.getDiscordUrl());}
				commRepo.saveAndFlush(managedCommunity);
			}
		}

		return managedCommunity;

	}

	@Override
	public void delete(int id) {
		if (commRepo.existsById(id)) {
			commRepo.deleteById(id);
		}
	}

	@Override
	public Community create(String username, Community community) {

		User user = userRepo.findByUsername(username);

		if (user != null) {
			// community.setUser(user);
			return commRepo.saveAndFlush(community);
		}

		return null;

	}



}
