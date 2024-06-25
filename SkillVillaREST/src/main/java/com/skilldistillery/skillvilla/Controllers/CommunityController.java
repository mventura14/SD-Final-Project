package com.skilldistillery.skillvilla.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.Community;
import com.skilldistillery.skillvilla.services.CommunityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommunityController {

	private CommunityService commService;

	public CommunityController(CommunityService commService) {
		super();
		this.commService = commService;
	}

	@GetMapping("communities")
	public List<Community> findAll(HttpServletRequest req, HttpServletResponse res) {

		List<Community> communities = commService.findAll();

		if (communities.isEmpty()) {
			res.setStatus(204);
		}

		return communities;
	}

	@GetMapping("communities/{communityId}")
	public Community show(HttpServletRequest req, HttpServletResponse res, @PathVariable("communityId") int id) {

		Community community = commService.show(id);

		if (community == null) {
			res.setStatus(404);
		}

		return community;
	}

	@PostMapping("communities")
	public Community createPost(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@RequestBody Community community) {

		Community newCommunity = null;

		try {
			newCommunity = commService.create(principal.getName(), community);

			if (newCommunity != null) {
				res.setStatus(201);
				res.setHeader("location",
						req.getRequestURL().append("/posts/").append(newCommunity.getId()).toString());
			} else {
				res.setStatus(401);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return newCommunity;

	}

	@PutMapping("communities/{communityId}")
	public Community update(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("communityId") int communityId, @RequestBody Community community) {
		
		Community updated = null;

		try {
			updated = commService.update(principal.getName(), communityId, community);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return updated;
}

//	@DeleteMapping("communities/{id}")
//	public void delete(@PathVariable("id") int id) {
//		commService.delete(id);
//	}
}
