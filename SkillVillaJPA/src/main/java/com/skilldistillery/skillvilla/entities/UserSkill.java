package com.skilldistillery.skillvilla.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_has_skill")
public class UserSkill {
	
	@EmbeddedId
	private UserSkillId id;
	
	private String level;
	
	@JsonIncludeProperties({"id","username"})
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	@MapsId(value = "skillId")
	private Skill skill;
	
	//--------------------------------------------------------------------------

	public UserSkill() {
		super();
	}

	public UserSkillId getId() {
		return id;
	}

	public void setId(UserSkillId id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
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
		UserSkill other = (UserSkill) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserSkill [id=" + id + ", level=" + level + "]";
	}
	
	
	
	
}