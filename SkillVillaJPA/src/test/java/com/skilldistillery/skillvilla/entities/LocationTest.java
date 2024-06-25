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

class LocationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Location location;
	
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
		 location = em.find(Location.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		location = null;
	}
	
	
	@Test
	void location_basic_mapping() {
		assertNotNull(location);
		assertEquals("Vienna", location.getCity());
	}
	
	@Test
	void location_MTO_communities() {
		assertNotNull(location);
		assertNotNull(location.getCommunities());
		assertTrue(location.getCommunities().size() > 0);
		assertTrue(location.getCommunities().size() >= 1);
	}
	
	@Test
	void location_MTO_post() {
		assertNotNull(location);
		assertNotNull(location.getPosts());
		assertTrue(location.getPosts().size() > 0);
		assertTrue(location.getPosts().size() >= 1);
	}
	
	@Test
	void location_MTO_user() {
		assertNotNull(location);
		assertNotNull(location.getUsers());
		assertTrue(location.getUsers().size() > 0);
		assertTrue(location.getPosts().size() >= 1);
	}
	
	@Test
	void location_OTM_communityEvent() {
		assertNotNull(location);
		assertNotNull(location.getCommunityEvents());
		assertTrue(location.getCommunityEvents().size() > 0);
		assertTrue(location.getCommunityEvents().size() >= 1);

	}
	
}
