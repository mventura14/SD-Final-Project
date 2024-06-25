package com.skilldistillery.skillvilla.services;

import java.util.List;

import com.skilldistillery.skillvilla.entities.Skill;
import com.skilldistillery.skillvilla.entities.SkillCategory;

public interface SkillService {

	List<Skill> indexSkills();

	List<SkillCategory> indexSkillCategories();

	boolean addUserSkill(String username, int skillId, String level);
}
