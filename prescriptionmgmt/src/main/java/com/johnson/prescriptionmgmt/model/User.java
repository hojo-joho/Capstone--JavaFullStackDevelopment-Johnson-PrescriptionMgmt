package com.johnson.prescriptionmgmt.model;

import jakarta.persistence.Column;
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
 * POJO for User informaion
 * @author Hillary
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String fullName;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	private String role;
	
	@ManyToOne
	private Pharmacy pharmacy;
	
	public User (String fullName, String email, String password) {
		this.fullName=fullName;
		this.email=email;
		this.password=password;
	}
}
