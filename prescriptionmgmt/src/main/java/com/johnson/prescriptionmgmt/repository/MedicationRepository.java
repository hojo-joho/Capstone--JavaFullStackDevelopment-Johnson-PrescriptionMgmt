package com.johnson.prescriptionmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;

/**
 * MedicationRepository
 * extends JpaRepository for CRUD operations
 * defines custom query for finding medications needing refills
 * @author Hillary
 *
 */
@Repository
public interface MedicationRepository extends JpaRepository <Medication, Integer> {

	@Query("select m from Medication m where m.needsRefill=1" )
	List<Medication> findAllMedsNeedingRefills();
	
	
	List<Medication> findMedsByUser(User user);
}
