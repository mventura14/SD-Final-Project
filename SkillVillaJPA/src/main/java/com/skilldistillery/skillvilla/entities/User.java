package com.skilldistillery.skillvilla.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Column(name="image_url")
	private String imageUrl;

	private boolean enabled;
	
	private String role;
	
	private String bio;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <CommunityEvent> comEvents;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <Comment> comments;
	
	@JsonIgnoreProperties({"posts"})
	@ManyToMany(mappedBy="communityMembers")
	private List <Community> communities;
	
	@JsonIgnore
	@ManyToMany(mappedBy="users")
	private List <CommunityEvent> communityEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<CommunityEvent> postedEvent;
	
	
	@OneToMany(mappedBy = "user")
	private List<UserSkill> skills;
	
	//---------------------------------------------------------------------------
	
	public List<UserSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<UserSkill> skills) {
		this.skills = skills;
	}

	public User() {}

	//---------------------------------------------------------------------------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	public List<CommunityEvent> getComEvents() {
		return comEvents;
	}

	public void setComEvents(List<CommunityEvent> comEvents) {
		this.comEvents = comEvents;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<CommunityEvent> getCommunityEvents() {
		return communityEvents;
	}

	public void setCommunityEvents(List<CommunityEvent> communityEvents) {
		this.communityEvents = communityEvents;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<CommunityEvent> getPostedEvent() {
		return postedEvent;
	}

	public void setPostedEvent(List<CommunityEvent> postedEvent) {
		this.postedEvent = postedEvent;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", imageUrl=" + imageUrl + ", enabled=" + enabled + ", role=" + role + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt;
	}
	

	

}