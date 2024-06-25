package com.skilldistillery.skillvilla.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillvilla.entities.Comment;
import com.skilldistillery.skillvilla.entities.Post;
import com.skilldistillery.skillvilla.services.CommentService;
import com.skilldistillery.skillvilla.services.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class PostController {

	private PostService postService;
	private CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		super();
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping("communities/{communityId}/posts")
	public List<Post> index(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("communityId") int communityId) {

		List<Post> posts = postService.index(communityId);

		if (posts.isEmpty()) {
			res.setStatus(204);
		}

		return posts;
	}

	@GetMapping("communities/{communityId}/posts/{postId}")
	public Post show(HttpServletRequest req, HttpServletResponse res, @PathVariable("communityId") int communityId,
			@PathVariable("postId") int postId) {

		Post post = postService.show(postId, communityId);

		if (post == null) {
			res.setStatus(404);
		}

		return post;
	}

	@PostMapping("communities/{communityId}/posts")
	public Post createPost(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("communityId") int communityId, @RequestBody Post post) {
		System.out.println("conroller" + post);
		Post newPost = null;

		try {
			newPost = postService.create(principal.getName(), communityId, post);

			if (newPost != null) {
				res.setStatus(201);
				res.setHeader("location", req.getRequestURL().append("/posts/").append(newPost.getId()).toString());
			} else {
				res.setStatus(401);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return newPost;

	}

	@PostMapping("communities/{communityId}/posts/{postId}")
	public Comment createCommentOnPost(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("communityId") int communityId, @PathVariable("postId") int postId,
			@RequestBody Comment comment) {

		Comment newComment = null;

		try {
			newComment = commentService.createOnPost(principal.getName(), communityId, postId, comment);

			if (newComment != null) {
				res.setStatus(201);
				res.setHeader("location",
						req.getRequestURL().append("/posts/comments/").append(newComment.getId()).toString());
			} else {
				res.setStatus(401);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

		return newComment;

	}

	@PutMapping("communities/{communityId}/posts/{postId}")
	public Post update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable("postId") int postId,
			@RequestBody Post post) {
		Post updated = null;
		
		try {
			updated = postService.update(principal.getName(), postId, post);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
		
		
		return updated;
	}

	@DeleteMapping("communities/{communityId}/posts/{postId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("postId") int postId) {
		boolean deleted = postService.destroy(principal.getName(), postId);

		if (deleted) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			res.setStatus(404);
		}

	}

}
