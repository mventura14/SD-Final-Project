package com.skilldistillery.skillvilla.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class SkillTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Skill skill;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		emf = Persistence.createEntityManagerFactory("SkillVillaJPA");
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@BeforeEach
	 void setUp() throws Exception {
		 em = emf.createEntityManager();  
		 skill = em.find(Skill.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		skill = null;
	}
	
	
	@Test
	void skill_basic_mapping() {
		assertNotNull(skill);
		assertEquals("Baking", skill.getName());
	}

	@Test
	void skill_MTO_skillCategory() {
		assertNotNull(skill);
		assertEquals("Culinary", skill.getCategory().getName());
	}
	
	@Test
	void skill_MTM_community() {
		assertNotNull(skill);
		assertNotNull(skill.getCommunities());
		assertTrue(skill.getCommunities().size() > 0);
		assertTrue(skill.getCommunities().size() >= 1);
	}
	
	@Test
	void skill_MTM_userSkill() {
		assertNotNull(skill);
		assertNotNull(skill.getUsers());
		assertTrue(skill.getUsers().size() > 0);
		assertTrue(skill.getUsers().size() >= 1);
		assertEquals("Expert", skill.getUsers().get(0).getLevel());
	}
}
