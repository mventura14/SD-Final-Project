package com.skilldistillery.skillvilla.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.CommentRepository;
import com.skilldistillery.skillvilla.Repositories.PostRepository;
import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.entities.Comment;
import com.skilldistillery.skillvilla.entities.Post;
import com.skilldistillery.skillvilla.entities.User;

@Service
public class CommentServiceImpl implements CommentService {

	private PostRepository postRepo;
	private UserRepository userRepo;
	private CommentRepository commentRepo;

	public CommentServiceImpl(PostRepository postRepo, UserRepository userRepo, CommentRepository commentRepo) {
		super();
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.commentRepo = commentRepo;
	}

	@Override
	public Comment createOnPost(String username, int communityId, int postId, Comment comment) {

		User user = userRepo.findByUsername(username);
		Post post = postRepo.findByIdAndCommunityId(postId, communityId);
		if (user != null & post != null) {
			comment.setUser(user);
			comment.setPost(post);
			return commentRepo.saveAndFlush(comment);
		}

		return null;

	}

	@Override
	public boolean destroy(String username,int postId, int commentId) {

		if (postRepo.existsById(postId)) {
			if (commentRepo.existsByIdAndPostIdAndUserUsername(commentId, postId, username)) {
				commentRepo.deleteById(commentId);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Comment> index(int postId) {
		return commentRepo.findAllByPostId(postId);
	}

	@Override
	public Comment update(String username, int commentId, Comment comment) {
		Optional<Comment> postOptional = commentRepo.findById(commentId);
		Comment managedComment = null;
		
		if (commentRepo.existsByIdAndUserUsername(commentId,username)) {
			if (postOptional.isPresent()) {
				managedComment = postOptional.get();
				
				if(comment.getMessage() != null && !comment.getMessage().isBlank()){ managedComment.setMessage(comment.getMessage());};
			
				
				
				commentRepo.saveAndFlush(managedComment);
				
			}
		}
		
		return managedComment;
	}


}
