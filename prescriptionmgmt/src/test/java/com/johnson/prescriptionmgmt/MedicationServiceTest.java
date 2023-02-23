package com.johnson.prescriptionmgmt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.johnson.prescriptionmgmt.repository.MedicationRepository;
import com.johnson.prescriptionmgmt.service.MedicationService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class MedicationServiceTest {
	
	@Mock
	private MedicationRepository medRepo;
	private MedicationService medService;
	
	@BeforeEach
	void setUpService() {
		medService = new MedicationService(medRepo);
	}
	
	
	@Test
	public void testFindAllMeds() {
		
		medService.findAll();
		
		Mockito.verify(medRepo).findAll();
	}
}
