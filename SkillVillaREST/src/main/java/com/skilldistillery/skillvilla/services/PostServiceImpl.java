package com.skilldistillery.skillvilla.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skilldistillery.skillvilla.Repositories.CommunityRepository;
import com.skilldistillery.skillvilla.Repositories.PostRepository;
import com.skilldistillery.skillvilla.Repositories.UserRepository;
import com.skilldistillery.skillvilla.entities.Post;
import com.skilldistillery.skillvilla.entities.User;

@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepo;
	private UserRepository userRepo;
	private CommunityRepository commRepo;

	public PostServiceImpl(PostRepository postRepo,UserRepository userRepo,CommunityRepository commRepo) {
		super();
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.commRepo = commRepo;
	}

	@Override
	public List<Post> index(int comunityId) {
		return postRepo.findAllByCommunityId(comunityId);
	}

	@Override
	public Post show(int postId, int communityId) {
		Post post = postRepo.findByIdAndCommunityId(postId, communityId);
		
		return post;
	}

	@Override
	public Post create(String username, int communityId, Post post) {
		
		User user = userRepo.findByUsername(username);
		
		if (user != null) {
			
			post.setCommunity(commRepo.findById(communityId).get());
			post.setUser(user);
			System.out.println(post);
			return postRepo.saveAndFlush(post);
		}

		return null;
		
	}

	@Override
	public Post update(String username ,int postId, Post post) {
		Optional<Post> postOptional = postRepo.findById(postId);
		Post managedPost = null;
		
		if (postRepo.existsByIdAndUserUsername( postId,username)) {
			if (postOptional.isPresent()) {
				managedPost = postOptional.get();
				
				if(post.getDescription() != null && !post.getDescription().isBlank()){ managedPost.setDescription(post.getDescription());};
				if(post.getLocation() != null) {managedPost.setLocation(post.getLocation());};
				if(post.getImageUrl() != null && !post.getImageUrl().isBlank() || post.getImageUrl() == " ") {managedPost.setImageUrl(post.getImageUrl());}
				if(post.getPostCategory() != null) { managedPost.setPostCategory(post.getPostCategory());};
				if(post.getLocation() != null) {managedPost.setLocation(post.getLocation());}
				
				//managedPost.setEnabled(post.isEnabled());
				postRepo.saveAndFlush(managedPost);
				
			}
		}
		
		return managedPost;
	}

	@Override
	public void delete(int id) {
		if(postRepo.existsById(id)) {
			postRepo.deleteById(id);
		}
		
	}

	@Override
	public boolean destroy(String username, int postId) {
		if (postRepo.existsByIdAndUserUsername(postId, username)) {
			postRepo.deleteById(postId);
			return true;
		}

		return false;
	}

	
	
	
	
	
}
