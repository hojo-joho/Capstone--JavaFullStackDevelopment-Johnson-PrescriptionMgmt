package com.johnson.prescriptionmgmt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.UserRepository;

/**
 * Custom UserDetailsService
 * necessary for authentication via Spring Security
 * must define where to find user credential and handles exception for user not found
 * @author Hillary
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}

	/**
	 *User details returns UserDetails object used for authentication and authorization
	 *Details are storred in authentication manager throughout session
	 *Translates user role from string to SimpleGrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			System.out.print("User was null");
			throw new UsernameNotFoundException("That that email is not associated with any account");
		} else {
			List<String> userRoles = new ArrayList<String>();
			userRoles.add(user.getRole());
			System.out.println("USER DETAILS :" + user.toString());
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(), 
					user.getPassword(),
					mapUserRolesToAuth(userRoles));
		}
	}
	
	/**
	 * Necessary to translate role into collection of authorites for user details
	 * @param roles (list created in LoadUserByUsername method)
	 * @return Mapped collection of Granted Authorities based on role
	 */
	private Collection < ? extends GrantedAuthority> mapUserRolesToAuth(Collection<String> roles) {
		String userRole = roles.iterator().next();
		Collection < ? extends GrantedAuthority> roleToAuthority = roles.stream().map(role -> new SimpleGrantedAuthority(userRole))
				.collect(Collectors.toList());
		
		return roleToAuthority;
	}

}
