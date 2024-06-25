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

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private  Comment comment;
	
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
		 comment = em.find(Comment.class, 1);
	 }
			
	@AfterEach
	 void tearDown() throws Exception {
		em.close();
		comment= null;
	}
	
	@Test
	void test_Comment() {
		assertNotNull(comment);
		assertEquals("Cant Wait!", comment.getMessage());
		
	}
	
	@Test
	void test_Comment_Has_Post () {
		assertNotNull(comment);
		assertNotNull(comment.getPost());
		assertEquals("Everyone join the denver womans snowboarding community!", comment.getPost().getDescription());
	}
	
	@Test
	void test_Comment_Has_User () {
		assertNotNull(comment);
		assertNotNull(comment.getUser());
		assertEquals("test", comment.getUser().getUsername());
	}
}
