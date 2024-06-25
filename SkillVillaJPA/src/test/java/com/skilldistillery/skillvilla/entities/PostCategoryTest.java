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

class PostCategoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PostCategory postCat;
	
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
		 postCat = em.find(PostCategory.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		postCat = null;
	}
	
	
	@Test
	void test_post_category() {
		assertNotNull(postCat);
		assertEquals("Culinary", postCat.getName());
		
	}
	
	@Test
	void category_OTM_post() {
		assertNotNull(postCat);
		assertNotNull(postCat.getPosts());
		assertTrue(postCat.getPosts().size() > 0);
		assertTrue(postCat.getPosts().size() >= 1);
	}

}
