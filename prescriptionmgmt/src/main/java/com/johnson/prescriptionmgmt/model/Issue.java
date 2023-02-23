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
 * POJO for issues reported to ADMIN via user homepage
 * @author Hillary
 *
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String issueTxt;
	private Boolean resolved;
	@ManyToOne
	private User user;
	
	public Issue(String issueTxt, Boolean resolved) {
		this.issueTxt=issueTxt;
		this.resolved=resolved;
	}
}
