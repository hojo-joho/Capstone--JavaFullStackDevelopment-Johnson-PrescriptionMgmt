package com.johnson.prescriptionmgmt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO for Medication added/updated/deleted by User and Updated (refillDate only) by admin
 * @author Hillary
 *
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String medicationName;
	private String directions;
	private String daysSupply;
	private String refillDate;
	private Boolean needsRefill;
	@ManyToOne
	private User user;

	
	public Medication (String name, String directions, String daysSupply, Boolean needsRefill, String refillDate, User user) {
		this.medicationName= name;
		this.directions = directions;
		this.daysSupply= daysSupply;
		this.needsRefill= needsRefill;
		this.refillDate=refillDate;
		this.user= user;
	}
	public Medication (String name, String directions, String daysSupply, String refillDate, Boolean needsRefill) {
		this.medicationName= name;
		this.directions = directions;
		this.daysSupply= daysSupply;
		this.needsRefill= needsRefill;
	}
}
