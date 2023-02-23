package com.johnson.prescriptionmgmt;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.UserRepository;
import org.junit.Assert;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testAddNew() {
		
		User testuser = new User();
		
		testuser.setFullName("Toaster");
		testuser.setEmail("T@gmail.com");
		testuser.setPassword("toaster");
		
		userRepo.save(testuser);
		
		User returned = userRepo.findByEmail("T@gmail.com");
		
		
		Assert.assertEquals(testuser.getEmail(), returned.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		
		User testuser = new User();
		
		testuser.setFullName("Otter");
		testuser.setEmail("ODog@gmail.com");
		testuser.setPassword("otter");
		
		userRepo.save(testuser); 
		
		User retrieve = userRepo.findByEmail("ODog@gmail.com");
		
		Assert.assertEquals(testuser.getEmail(), retrieve.getEmail());
	}
	
	@Test
	public void testFindAllUserAlphabetical() {
		
		User userA = new User("Zebra", "z@email.com", "1234");
		userRepo.save(userA);
		
		User userB = new User("Adele", "a@email.com", "1234");
		userRepo.save(userB);
		
		List<User> findAll = userRepo.findAll();
		User firstInAll = findAll.get(0);
		int lengthFindAll = findAll.size();
		
		List<User> findAlphabetical = userRepo.findAllUserAlphabetical();
		User firstInAlphabetical = findAlphabetical.get(0);
		int lengthAlphabetical = findAlphabetical.size();
		
		Assert.assertEquals(lengthFindAll, lengthAlphabetical);
		Assert.assertNotEquals(firstInAll, firstInAlphabetical);
		
		
	}

}
