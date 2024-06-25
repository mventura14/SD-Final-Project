package com.skilldistillery.skillvilla.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillvilla.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	boolean existsByIdAndPostIdAndUserUsername(int commentId, int postId, String username);

	List<Comment> findAllByPostId(int postId);

	boolean existsByIdAndUserUsername(int commentId, String username);

}
