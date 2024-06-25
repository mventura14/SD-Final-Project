package com.skilldistillery.skillvilla.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.Skill;
import com.skilldistillery.skillvilla.entities.SkillCategory;
import com.skilldistillery.skillvilla.services.SkillService;
import com.skilldistillery.skillvilla.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class SkillController {

	private UserService userService;
	private SkillService skillService;


	public SkillController(UserService userService, SkillService skillService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
	}
	
	@GetMapping("skills")
	public List<Skill> indexSkill(HttpServletRequest req, HttpServletResponse res){
		List<Skill> Skills = skillService.indexSkills();

		if (Skills.isEmpty()){
			res.setStatus(204);
		}

		return Skills;
	}
	
	@GetMapping("skillCategories")
	public List<SkillCategory> indexSkillCategory(HttpServletRequest req, HttpServletResponse res){
		List<SkillCategory> Skills = skillService.indexSkillCategories();

		if (Skills.isEmpty()){
			res.setStatus(204);
		}

		return Skills;
	}

	

	
}
