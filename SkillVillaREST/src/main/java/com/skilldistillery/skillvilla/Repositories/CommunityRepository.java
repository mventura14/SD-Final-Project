package com.skilldistillery.skillvilla.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillvilla.entities.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

	
}
