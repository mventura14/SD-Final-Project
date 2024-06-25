package com.skilldistillery.skillvilla.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillvilla.entities.UserSkill;
import com.skilldistillery.skillvilla.entities.UserSkillId;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId>{

	
	
	
}
