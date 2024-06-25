package com.skilldistillery.skillvilla.Controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.User;
import com.skilldistillery.skillvilla.services.AuthService;
import com.skilldistillery.skillvilla.services.SkillService;
import com.skilldistillery.skillvilla.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {

	private UserService userService;
	private SkillService skillService;
	private AuthService	authService;

	public UserController(UserService userService, SkillService skillService, AuthService	authService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
		this.authService = authService;
	}

//	@GetMapping("users")
//	public List<User> findAll(){
//		return userService.findAll();
//	}

//	@GetMapping("users")
//	public User show(HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		return userService.show(id);
//	}

//	@PostMapping("users")
//	public User create(@RequestBody User user) {
//		return userService.create(user);
//	}

	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("userId") int userId, @RequestBody User user) {
		User updated = null;

		try {
			updated = userService.update(principal.getName(), userId, user);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return updated;

	}

//	@DeleteMapping("users/{id}")
//	public void delete(@PathVariable("id") int id) {
//		userService.delete(id);
//	}

	@PutMapping("users/{userId}/skills/{skillId}")
	public User postMethodName(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("skillId") int skillID, @RequestBody String level) {
		
		
		boolean skillAdded = skillService.addUserSkill(principal.getName(), skillID, level);

		if(skillAdded) {
			return authService.getUserByUsername(principal.getName());
		}
		return null;
	}

}
