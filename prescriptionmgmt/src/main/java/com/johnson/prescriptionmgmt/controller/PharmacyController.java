package com.johnson.prescriptionmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnson.prescriptionmgmt.dto.CurrentUser;
import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.service.PharmacyService;
import com.johnson.prescriptionmgmt.service.UserService;

/**
 * Conroller for populating pharmacy page based on user credentials
 * @author Hillary
 *
 */
@Controller
public class PharmacyController {
	
	@Autowired
	PharmacyService pharmService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping("/pharmacy")
	public String goToPharmacy(Model model) {
		String userEmail = CurrentUser.getCurrentUserEmail();
		User currentUser = userService.findUserByEmail(userEmail);
		
		String pharmName = currentUser.getPharmacy().getPharmacyName();
		
		Pharmacy pharmacy = pharmService.findPharmacyByName(pharmName);
			
		model.addAttribute("pharmacyName", pharmacy.getPharmacyName());
		model.addAttribute("pharmacyPhone", pharmacy.getPharmacyPhone());
		model.addAttribute("pharmacyHours", pharmacy.getPharmacyHours());
		return "pharmacy";
	}
}
