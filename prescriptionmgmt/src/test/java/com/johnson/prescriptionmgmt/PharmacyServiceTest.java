package com.johnson.prescriptionmgmt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.repository.PharmacyRepository;
import com.johnson.prescriptionmgmt.service.PharmacyService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class PharmacyServiceTest {
	
	@Mock
	private PharmacyRepository pharmRepo;
	private PharmacyService pharmService;
	
	@BeforeEach
	void setUpService() {
		pharmService = new PharmacyService(pharmRepo);
	}
	
	@Test
	public void testNewPharmacy() {
		
		Pharmacy pharm = new Pharmacy(); 
		
		pharmService.newPharmacy(pharm);
		
		Mockito.verify(pharmRepo).save(pharm);
	}
	
	
}
