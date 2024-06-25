package com.skilldistillery.skillvilla.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillvilla.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	
	@Override
	List <Post> findAll();
	
	Optional<Post> findById(int id);

	Post findByIdAndCommunityId(int postId, int communityId);

	List<Post> findAllByCommunityId(int comunityId);

	char[] existsByIdAndCommunity_id(int postId, int communityId);

	boolean existsByIdAndUserUsername(int postId, String username);

	boolean existsByIdAndUserUsernameAndCommunityId(int postId, String username, int communityId);

	boolean existsByIdAndCommunityId(int postId, int communityId);

	
}
