package com.johnson.prescriptionmgmt.dto;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Access information about logged in user
 * @author Hillary
 *
 */
public class CurrentUser {
	
	/**
	 * Access username via authentication credentials
	 * @return Logged in user's username
	 */
	public static String getCurrentUserEmail() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	
	}

}
