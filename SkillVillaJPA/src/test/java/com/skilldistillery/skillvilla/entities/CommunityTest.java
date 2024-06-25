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

class CommunityTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Community community;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SkillVillaJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {  
		em = emf.createEntityManager();
		community = em.find(Community.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_community() {
		assertNotNull(community);
		assertEquals(1, community.getId());
	}

	@Test
	void community_MTM_users() {
		assertNotNull(community);
		assertNotNull(community.getCommunityMembers());
		assertTrue(community.getCommunityMembers().size() > 0);
	}

	@Test
	void community_MTO_location() {
		assertNotNull(community);
		assertNotNull(community.getLocation());
		assertEquals("Vienna", community.getLocation().getCity());
	}

	@Test
	void community_OTM_post() {
		assertNotNull(community);
		assertNotNull(community.getPosts());
		assertTrue(community.getPosts().size() > 0);
		//assertTrue(community.getPosts().size() > 1);
	}
	
	@Test
	void community_MTM_skill() {
		assertNotNull(community);
		assertNotNull(community.getSkills());
		assertTrue(community.getSkills().size() > 0);
		assertTrue(community.getSkills().size() >= 1);
	}
	
	@Test
	void community_OTM_communityEvent() {
		assertNotNull(community);
		assertNotNull(community.getCommunityEvents());
		assertTrue(community.getCommunityEvents().size() > 0); 
		assertTrue(community.getCommunityEvents().size() >= 1); 
	}
}
