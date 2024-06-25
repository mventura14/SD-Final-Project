package com.skilldistillery.skillvilla.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class SkillCategoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private SkillCategory skillCat;
	
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
		 skillCat = em.find(SkillCategory.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		skillCat = null;
	}
	
	
	@Test
	void test_user() {
		assertNotNull(skillCat);
		assertEquals("Automotive", skillCat.getName());
	}  
	
	@Test 
	void sCategory_OTM_skill(){
		skillCat = em.find(SkillCategory.class, 2);
		assertNotNull(skillCat);
		assertNotNull(skillCat.getSkills());
		assertTrue(skillCat.getSkills().size() > 0);
		//assertTrue(skillCat.getSkills().size() > 1);
		
	}

}