package com.johnson.prescriptionmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.MedicationRepository;

/**
 * Service class for Medications
 * defines JpaRepository methods needed
 * @author Hillary
 *
 */
@Service
public class MedicationService {

	@Autowired
	MedicationRepository medRepo;
	
	public MedicationService(MedicationRepository medRepo) {
		this.medRepo=medRepo;
	}

	public void saveMed(Medication med) {
		medRepo.save(med);
	}
	
	public List<Medication> findAll() {
		return medRepo.findAll();
	}
	
	public List<Medication> findAllMedsNeedingRefills(){
		return medRepo.findAllMedsNeedingRefills();
	}
	
	public List<Medication> findMedsByUser(User user) {
		return medRepo.findMedsByUser(user);
	}
	
	public Medication findMedById(int id) {
		
		return medRepo.getReferenceById(id);

	}
	
	public void deleteMed(Integer id) {
		medRepo.deleteById(id);
	}
}
