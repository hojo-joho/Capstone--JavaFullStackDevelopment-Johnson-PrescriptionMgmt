package com.johnson.prescriptionmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.johnson.prescriptionmgmt.dto.UserDto;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.service.UserService;

import jakarta.validation.Valid;

/**
 * Controller for publically accessibe (non restricted) pages
 * @author Hillary
 *
 */
@Controller
public class IndexPageController {
	
	static final String LOGIN = "/login";
	static final String ERROR = "error";
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder encoder;
	
	public IndexPageController(UserService userService) {
		this.userService=userService;
	}
	
	@RequestMapping("/login")
	public String goToLogin() {
		return "login";
	}
	
		
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView login() {
			System.out.println("User logged in");
		    return new RedirectView("/home");
	}
	
	@RequestMapping("/register")
	public String goToRegister(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping("/index")
	public String goToIndex() {
		return "index";
	}
	
	@PostMapping("/register/newuser")
	public String registerUser(@Valid @ModelAttribute("user") UserDto user, Model model, BindingResult res) {
		
		User db_user = userService.findUserByEmail(user.getEmail());
		
		if (db_user != null) {
			res.rejectValue("email", null, "This email is associated with an existing account");
		} if (res.hasErrors()) {
			model.addAttribute(ERROR, "error");
			model.addAttribute("user", user);
			return "/register";
		} else {
			System.out.println("saving user: " + user.toString());
			userService.saveUser(user);
			return LOGIN;
		}
	}
}