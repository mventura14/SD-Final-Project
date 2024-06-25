package com.skilldistillery.skillvilla.services;

import java.util.List;

import com.skilldistillery.skillvilla.entities.Comment;

public interface CommentService {

	Comment createOnPost(String name, int communityId, int postId, Comment comment);

	boolean destroy(String name, int communityId, int postId);

	List<Comment> index(int postId);

	Comment update(String name, int postId, Comment comment);

}
