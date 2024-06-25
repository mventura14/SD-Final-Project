package com.skilldistillery.skillvilla.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.SkillCategoryRepository;
import com.skilldistillery.skillvilla.Repositories.SkillRepository;
import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.Repositories.UserSkillRepository;
import com.skilldistillery.skillvilla.entities.Skill;
import com.skilldistillery.skillvilla.entities.SkillCategory;
import com.skilldistillery.skillvilla.entities.User;
import com.skilldistillery.skillvilla.entities.UserSkill;
import com.skilldistillery.skillvilla.entities.UserSkillId;

@Service
public class SkillServiceImpl implements SkillService {

	private SkillRepository skillRepo;
	private UserRepository userRepo;
	private SkillCategoryRepository skillCatRepo;
	private UserSkillRepository uSkillRepo;
	
	public SkillServiceImpl(SkillRepository skillRepo, UserRepository userRepo, SkillCategoryRepository skillCatRepo, UserSkillRepository uSkillRepo) {
		super();
		this.skillRepo = skillRepo;
		this.userRepo = userRepo;
		this.skillCatRepo = skillCatRepo;
		this.uSkillRepo = uSkillRepo;
	}

	@Override
	public List<Skill> indexSkills() {
		return skillRepo.findAll();

	}

	@Override
	public List<SkillCategory> indexSkillCategories() {
		return skillCatRepo.findAll();
	}
	
	@Override
	public boolean addUserSkill(String username, int skillId, String level) {
		User user = userRepo.findByUsername(username);
		Skill skill = skillRepo.findById(skillId).get();
		boolean invited = false;
		if(user != null && skill != null){
		//if(user != null) {
			UserSkillId id = new UserSkillId(user.getId(),skillId);
			UserSkill userSkill = new UserSkill();
			userSkill.setId(id);
			userSkill.setUser(user);
			userSkill.setSkill(skill);
			userSkill.setLevel(level);
			uSkillRepo.saveAndFlush(userSkill);
			invited = true;
		}
		return invited;
	}
}
