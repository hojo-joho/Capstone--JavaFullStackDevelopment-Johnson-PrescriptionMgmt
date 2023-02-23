package com.johnson.prescriptionmgmt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.MedicationRepository;
import com.johnson.prescriptionmgmt.repository.UserRepository;
import com.johnson.prescriptionmgmt.service.MedicationService;

import org.junit.Assert;
@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicationRepositoryTest {

	@Autowired
	UserRepository userRepo;
	@Autowired
	MedicationRepository medRepo;
	@Autowired
	MedicationService medService;
	
	@Test
	public void testAddNew() {
		
		Medication testmed = new Medication();
		
		testmed.setMedicationName("Drug#3"); 
		testmed.setDaysSupply("20");
		testmed.setDirections("Take with food");
		testmed.setNeedsRefill(true);
		
		Medication savemed = medRepo.save(testmed);
		String name = savemed.getMedicationName();

		assertEquals(name, (testmed.getMedicationName()));
	}
	
	@Test
	public void testFindAllMedsNeedingRefills() {
		List<Medication> medList = new ArrayList<Medication>();
		
		Medication testmed = new Medication();
		
		testmed.setMedicationName("Drug#5"); 
		testmed.setDaysSupply("20");
		testmed.setDirections("Take at the beach");
		testmed.setNeedsRefill(true);
		
		medRepo.save(testmed);
		
		medList = medRepo.findAllMedsNeedingRefills();
		
		Assert.assertNotNull(medList.get(0));
	}
	
	
	@Test
	public void testFindMedsByUser() {
		
		User user = new User("Hillary", "H@G.com", "12345");
		userRepo.save(user);
		
		Medication testmed = new Medication("Drug#5","Take while skydiving", "20", true, "12/02/2022", user);
		Medication testmed2  = new Medication("Drug#10","Take while sleeping", "20", false, "2/14/2023", user);
		
		medRepo.save(testmed);
		medRepo.save(testmed2);
		
		List<Medication> returnMeds = new ArrayList<Medication>();
		
		returnMeds = medRepo.findMedsByUser(user);
		
		Assert.assertEquals(testmed, returnMeds.get(0));
	}
}
