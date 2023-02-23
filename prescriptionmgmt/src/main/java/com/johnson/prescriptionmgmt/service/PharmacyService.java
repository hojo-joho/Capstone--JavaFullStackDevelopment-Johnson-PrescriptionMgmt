package com.johnson.prescriptionmgmt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.repository.PharmacyRepository;

/**
 * Service class for Pharmacies
 * defines JpaRepository methods needed
 * @author Hillary
 *
 */
@Service
public class PharmacyService {
	
	@Autowired
	PharmacyRepository pharmRepo;

	public PharmacyService(PharmacyRepository pharmRepo) {
		this.pharmRepo=pharmRepo;
	}
	
	public void newPharmacy(Pharmacy pharmacy) {
		
		pharmRepo.save(pharmacy);
	}
	
	public Pharmacy findPharmacyById(int id) {
		return pharmRepo.getReferenceById(id);
		
	}
	
	public Pharmacy findPharmacyByName(String pharmacyName) {
		return pharmRepo.findByPharmacyName(pharmacyName);
	}
	
	public void updatePharmacy(int id, Pharmacy update) {
		
		Optional<Pharmacy> pharmObj = pharmRepo.findById(id);
		
		if (pharmObj.isPresent()) {
			Pharmacy original = pharmObj.get();
			original.setPharmacyName(update.getPharmacyName());
			original.setPharmacyPhone(update.getPharmacyPhone());
			original.setPharmacyHours(update.getPharmacyHours());
			pharmRepo.save(original);	
		} 
	}
	
}
