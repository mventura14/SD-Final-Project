package com.skilldistillery.skillvilla.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UserSkillTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private  UserSkill userSkill;
	
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
		 userSkill = em.find(UserSkill.class, new UserSkillId(1,1));
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		userSkill= null;
	}
	
	@Test
	void userSkil_basic_test() {
		assertNotNull(userSkill);
		assertEquals("Expert", userSkill.getLevel());
	}
	
	@Test
	void userSkill_MTO_user() {
		assertNotNull(userSkill);
		assertNotNull(userSkill.getUser());
		assertEquals("test", userSkill.getUser().getUsername());
	}

	@Test
	void skill() {
		assertNotNull(userSkill);
		assertNotNull(userSkill.getSkill());
		assertEquals("Baking", userSkill.getSkill().getName());
	}
}
