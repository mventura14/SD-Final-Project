package com.skilldistillery.skillvilla.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	private String message;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@JsonIgnoreProperties({"location","comments"})
 	@ManyToOne
 	@JoinColumn (name = "post_id")
 	private Post post;
 	
	@JsonIncludeProperties({"id","username", "firstName", "lastName", "imageUrl"})
 	@ManyToOne
 	@JoinColumn (name = "user_id")
 	private User user;
 	
 	
	public Comment () {
		
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@CreationTimestamp
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	@UpdateTimestamp
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Comment other = (Comment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", updatedAt=" + updatedAt + ", message=" + message + ", createdAt=" + createdAt
				+ "]";
	}
	
}
