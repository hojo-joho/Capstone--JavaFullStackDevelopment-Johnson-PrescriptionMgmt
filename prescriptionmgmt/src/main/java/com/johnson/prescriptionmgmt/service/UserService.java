package com.johnson.prescriptionmgmt.service;

import com.johnson.prescriptionmgmt.model.Pharmacy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.dto.UserDto;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.PharmacyRepository;
import com.johnson.prescriptionmgmt.repository.UserRepository;
/**
 * Service class for Users
 * defines JpaRepository methods needed
 * @author Hillary
 *
 */
@Service
public class UserService {
	
	@Autowired
	PharmacyRepository pharmRepo;
	
	private PasswordEncoder encoder;
	private UserRepository userRepo;
	
	public UserService(PasswordEncoder encoder, UserRepository userRepo) {
		this.encoder=encoder;
		this.userRepo=userRepo;
	}
	
	
//Find a user by their email or return an empty user
	public User findUserByEmail(String email){
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return user;
		}
		return null;
	}

//Find a user by Id
	public Optional<User> findUserById(int id) {
		return userRepo.findById(id);
	}
	
//Create new User
	/**
	 * SaveUser
	 * Translates userDto object to a User in database with an encoded password 
	 * sets pharmacy on registration
	 * sets role on registration to USER
	 * @param userDto
	 */
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setPassword(encoder.encode(userDto.getPassword()));
		String role = "USER";
		user.setRole(role);
		Pharmacy pharm = pharmRepo.findByPharmacyName("Alpine Apothocary");
		if (pharm == null) {
			pharm = setPharmacy();
		}
		user.setPharmacy(pharm);
		System.out.println(user.toString());
		userRepo.save(user);
	}
	
//Find all users and return as DTOs
	/**
	 * FindAllUsers
	 * maps users to Dto objects
	 * @return List<UserDto>
	 */
	public List<UserDto> findAllUsers() {
		List<User> userList =  userRepo.findAll();
		
		List<UserDto> usersAsDtos = userList.stream()
			.map((user) -> mapUserToUserDto(user))
			.collect(Collectors.toList());
		
		return usersAsDtos;
	
	}

//Copy info from user (returned from db) to UserDto object
	/**
	 * MapUsertoUserDto
	 * Takes in a user and returns a UserDto
	 * used in findAllUsers method
	 * @param user
	 * @return
	 */
	private UserDto mapUserToUserDto (User user) {
		UserDto userDto = new UserDto();
		userDto.setFullName(user.getFullName());
		userDto.setEmail(user.getEmail());
		return userDto;	
	}

	/**
	 * Check for existance of pharmacy and create if necessary
	 * retuned to saveUser method to set pharmacy on registration
	 * @return pharmacy
	 */
	private Pharmacy setPharmacy() {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setPharmacyName("Alpine Apothocary");
		pharmacy.setPharmacyHours("Monday - Saturday, 8am-10pm");
		pharmacy.setPharmacyPhone("(406) 555-7294");
		pharmRepo.save(pharmacy);
		return pharmacy;	
	}
	
}
