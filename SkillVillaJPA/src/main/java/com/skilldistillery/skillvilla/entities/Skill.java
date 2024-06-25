package com.skilldistillery.skillvilla.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private SkillCategory category;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable (name="community_has_skill", joinColumns = @JoinColumn(name= "skill_id"), inverseJoinColumns= @JoinColumn(name="community_id"))
	private List<Community> communities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "skill")
	private List<UserSkill> users;
	
	//-----------------------------------------------------------------------------------------------------
	
	public Skill() {
		super();
	}
	
	//-----------------------------------------------------------------------------------------------------


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SkillCategory getCategory() {
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<UserSkill> getUsers() {
		return users;
	}

	public void setUsers(List<UserSkill> users) {
		this.users = users;
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
		Skill other = (Skill) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

	
}
