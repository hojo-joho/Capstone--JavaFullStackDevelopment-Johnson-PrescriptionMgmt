package com.johnson.prescriptionmgmt.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.johnson.prescriptionmgmt.dto.UserDto;
import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.service.MedicationService;
import com.johnson.prescriptionmgmt.service.UserService;

/**
 * Controller for endpoints accessible only with ADMIN authority
 * @author Hillary
 *
 */
@Controller
public class PharmacistController {
	
	@Autowired 
	MedicationService medService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/refills")
	public String viewRefills(Model model) {

		List<Medication> rxlist = medService.findAllMedsNeedingRefills();
		if (rxlist.isEmpty()) {
			model.addAttribute("norefills", "There are no Prescriptions to refill at this time.");
			return "/refills";
		} else {
			model.addAttribute("refills", rxlist);
			System.out.println(rxlist);
			return "/refills";
		} 
	
	}
	
	@GetMapping("/refills/update/{id}")
	public RedirectView refillRx(@PathVariable("id") Integer id) {
		Medication med = medService.findMedById(id);
		med.setNeedsRefill(false);
		String datepattern = "MM/dd/yyyy";
		String dateString =new SimpleDateFormat(datepattern).format(new Date());
		med.setRefillDate(dateString);
		medService.saveMed(med);
		return new RedirectView("/refills");
	}
	
	@RequestMapping("/users")
	public String viewUsers(Model model) {

		List<UserDto> users = userService.findAllUsers();
		if (users.isEmpty()) {
			model.addAttribute("nousers", "There are no users in the program.");
			return "/users";
		} else {
			
			model.addAttribute("users", users);
			return "/users";
		} 
	
	}

}
