package com.johnson.prescriptionmgmt;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.repository.PharmacyRepository;

@DataJpaTest
@Rollback(true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PharmacyRepositoryTest {

	@Autowired
	PharmacyRepository pharmRepo;
	
	@Test
	public void testFindByPharmacyName() {
		
		Pharmacy newPharm = new Pharmacy();
		newPharm.setPharmacyName("test");
		pharmRepo.save(newPharm);
		
		Pharmacy pharmacy = pharmRepo.findByPharmacyName("test");
		
		Assert.assertEquals(pharmacy, newPharm);
		
		pharmRepo.delete(newPharm);
	}
}
