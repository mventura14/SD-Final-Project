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

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
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
		 user = em.find(User.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
	}
	
	
	@Test
	void test_user() {
		assertNotNull(user);
		assertEquals("test", user.getUsername());
		assertEquals(true, user.isEnabled());
		
	}
	
	@Test
	void test_user_has_community_event() {
		assertNotNull(user);
		assertEquals("test", user.getUsername());
		//assertEquals(true, user.get);
		
	}
	
	@Test
	void user_OTM_post() {
		assertNotNull(user);
		assertNotNull(user.getPosts());
		assertTrue(user.getPosts().size() > 0);
		assertTrue(user.getPosts().size() >= 1);
	}
	
	@Test
	void user_OTM_comments() {
		assertNotNull(user);
		assertNotNull(user.getComments());
		assertTrue(user.getComments().size() > 0);
		assertTrue(user.getComments().size() >= 1);
		}
	
	@Test
	void user_MTO_location() {
		assertNotNull(user);
		assertNotNull(user.getLocation());
		assertEquals("Vienna", user.getLocation().getCity());
	}
	
	@Test
	void usert_MTM_communitymembers() {
		assertNotNull(user);
		assertNotNull(user.getCommunities());
		assertTrue(user.getCommunities().size() > 0);
		assertTrue(user.getCommunities().size() >= 1);
	}
	@Test
	void user_MTM_communityEvents() {
		assertNotNull(user);
		assertNotNull(user.getCommunityEvents());
		assertTrue(user.getCommunityEvents().size() > 0);
		assertTrue(user.getCommunityEvents().size() >= 1);
	}
	
	@Test
	void user_OTM_communityEvent() {
		assertNotNull(user);
		assertNotNull(user.getPostedEvent());
		assertTrue(user.getPostedEvent().size() > 0);
		assertTrue(user.getPostedEvent().size() >= 1);
	}
	
	@Test
	void user_OTM_userSkill() {
		assertNotNull(user);
		assertNotNull(user.getSkills());
		assertTrue(user.getSkills().size() > 0);
		assertTrue(user.getSkills().size() >= 1);
		assertEquals("Expert", user.getSkills().get(0).getLevel());
	}

}
