package com.johnson.prescriptionmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.Pharmacy;

/**
 * PharmacyRepository
 * extends JpaRepository for CRUD operations
 * defines additional method to return a pharmacy by name
 * @author Hillary
 *
 */
@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer>{

	Pharmacy findByPharmacyName(String pharmacyName);

}
