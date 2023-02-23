package com.johnson.prescriptionmgmt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object to varify that user registering has entered all necessary information
 * @author Hillary
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int id;
	@NotEmpty
	private String fullName;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;

}
