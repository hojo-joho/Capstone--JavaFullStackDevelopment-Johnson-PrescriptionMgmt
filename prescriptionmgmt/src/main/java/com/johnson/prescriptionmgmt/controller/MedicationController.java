package com.johnson.prescriptionmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.johnson.prescriptionmgmt.dto.CurrentUser;
import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.service.MedicationService;
import com.johnson.prescriptionmgmt.service.UserService;


/**
 * Controller for displaying/creating/updating/deleting medication information
 * @author Hillary
 *
 */
@Controller
public class MedicationController {
	
	@Autowired
	MedicationService medService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/addrx")
	public String goToAddRx(Model model) {
		Medication medication = new Medication();
		model.addAttribute(medication);
		return "addrx";
	}
	
	@PostMapping("/addrx/newmed")
	public String addRx(@ModelAttribute("medication") Medication medication, Model model, BindingResult res) {
		String userEmail = CurrentUser.getCurrentUserEmail();
		User currentUser = userService.findUserByEmail(userEmail);
		
		if (res.hasErrors()) {
			model.addAttribute("error", "error");
			model.addAttribute("pageName", "Add New Medication");
			return "/addrx";
		}else {
			medication.setUser(currentUser);
			medService.saveMed(medication);
			return "/home";
		}
	}
	
	@GetMapping("/addrx/update/{id}")
	public String updateMedication(@PathVariable("id") Integer id, Model model) {
		Medication med = medService.findMedById(id);
		model.addAttribute("medication", med);
		model.addAttribute("pageName", "Update Medication");
		return "addrx";
	}
	
	@PostMapping("/addrx/save")
	public RedirectView updateRx(@ModelAttribute("medication") Medication medication, Model model, BindingResult res) {
		if (res.hasErrors()) {
			model.addAttribute("error", "error");
			model.addAttribute("pageName", "Update Medication");
			return  new RedirectView("/addrx");
		}else {
			medService.saveMed(medication);
			return new RedirectView( "/home");
		}
		
		
	}
}
