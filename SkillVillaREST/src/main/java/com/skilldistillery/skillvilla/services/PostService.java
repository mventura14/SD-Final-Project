package com.skilldistillery.skillvilla.services;

import java.util.List;

import com.skilldistillery.skillvilla.entities.Post;

public interface PostService {

	
	
	Post show(int postId, int communityId);
	
	Post create(String string, int communityId, Post post);
	
	Post update(String username, int postId, Post post);
	
	void delete(int id);

	List<Post> index(int communityId);

	boolean destroy(String name, int postId);
}
