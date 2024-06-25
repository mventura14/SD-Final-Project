package com.skilldistillery.skillvilla.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private int zipcode;
	
	@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<Community> communities;
	
	@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<Post> posts;
	
	@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<CommunityEvent> communityEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<User> users;
	
	//----------------------------------------------------------------
	
	Location(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<CommunityEvent> getCommunityEvents() {
		return communityEvents;
	}

	public void setCommunityEvents(List<CommunityEvent> communityEvents) {
		this.communityEvents = communityEvents;
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
		Location other = (Location) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + "]";
	};

	
}
