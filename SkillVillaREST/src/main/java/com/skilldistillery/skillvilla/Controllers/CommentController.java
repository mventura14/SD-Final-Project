package com.skilldistillery.skillvilla.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.Comment;
import com.skilldistillery.skillvilla.services.CommentService;
import com.skilldistillery.skillvilla.services.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommentController {

	private PostService postService;
	private CommentService commentService;

	public CommentController(PostService postService, CommentService commentService) {
		super();
		this.postService = postService;
		this.commentService = commentService;
	}

//	@PutMapping("communities/{communityId}/posts/{postId}")
//	public Post update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable("postId") int postId,
//			@RequestBody Post post) {
//		Post updated = null;
//		
//		try {
//			updated = postService.update(principal.getName(), postId, post);
//			if (updated == null) {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//		
//		
//		return updated;
//	}

	@GetMapping("posts/{postId}/comments")
	public List<Comment> index(HttpServletRequest req, HttpServletResponse res, @PathVariable("postId") int postId) {

		List<Comment> comments = commentService.index(postId);

		if (comments.isEmpty()) {
			res.setStatus(204);
		}

		return comments;
	}

	@DeleteMapping("posts/{postId}/comments/{commentId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("postId") int postId, @PathVariable("commentId") int commentId) {
		boolean deleted = commentService.destroy(principal.getName(), postId, commentId);

		if (deleted) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			res.setStatus(404);
		}

	}

	@PutMapping("posts/{postId}/comments/{commentId}")
	public Comment update(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("postId") int postId, @PathVariable("commentId") int commentId,@RequestBody Comment comment) {
		Comment updated = null;

		try {
			updated = commentService.update(principal.getName(), postId, comment);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return updated;
	}

}
