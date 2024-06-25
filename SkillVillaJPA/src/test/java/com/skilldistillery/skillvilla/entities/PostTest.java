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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;
	
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
		 post = em.find(Post.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		post = null;
	}
	
	
	@Test
	void test_post() {
		assertNotNull(post);
		assertEquals(1, post.getId());		
	}
	
	@Test
	void post_MTO_community() {
		assertNotNull(post);
		assertNotNull(post.getCommunity());
		assertEquals("Denver Women Over 30 Snowboarders",post.getCommunity().getName());
	}
	
	@Test
	void post_MTO_postCategory() {
		assertNotNull(post);
		assertNotNull(post.getPostCategory());
		assertEquals("Culinary", post.getPostCategory().getName());
	}

	@Test
	void post_MTO_location() {
		assertNotNull(post);
		assertNotNull(post.getLocation());
		assertEquals("Vienna", post.getLocation().getCity());
	}
	
	@Test
	void post_MTO_user(){
		assertNotNull(post);
		assertNotNull(post.getUser());
		assertEquals("test", post.getUser().getUsername());
	}
	
	@Test
	void post_OTM_comment() {
		assertNotNull(post);
		assertNotNull(post.getComments());
		assertTrue(post.getComments().size() > 0);
		assertTrue(post.getComments().size() >= 1);
	}
}
