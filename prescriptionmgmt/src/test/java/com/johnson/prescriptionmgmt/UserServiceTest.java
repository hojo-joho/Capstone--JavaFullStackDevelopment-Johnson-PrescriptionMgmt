package com.johnson.prescriptionmgmt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.johnson.prescriptionmgmt.repository.UserRepository;
import com.johnson.prescriptionmgmt.service.UserService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
		
		@Mock
		private UserRepository userRepo;
		private UserService userService;
		private PasswordEncoder encoder;
		
		@BeforeEach
		void setUpService() {
			userService = new UserService(encoder, userRepo);
		}
		
		@Test
		public void testFindUserById() {
			
			userService.findUserById(0);
			
			Mockito.verify(userRepo).findById(0);
		}
}
