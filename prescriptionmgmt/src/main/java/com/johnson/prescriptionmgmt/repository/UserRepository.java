package com.johnson.prescriptionmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.User;

/**
 * UserRepository
 * extends JpaRepository for CRUD operations
 * defines additional method to find user by email 
 * defines custom query for finding all users ordered alphabetically
 * @author Hillary
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	  User findByEmail(String email);
	  
	  @Query("select u from User u ORDER BY u.fullName")
	  List<User> findAllUserAlphabetical();

}
