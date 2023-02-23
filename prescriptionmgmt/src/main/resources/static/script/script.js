/**
 * 
 */
 
 const userForm = document.querySelector(registerForm)
 const userEmail = document.getElementById(email)
 const userPass = document.getElementById(password)
 const fullName = document.getElementById(fullName)
 
 function validateRegistration() {
	 if(validateEmail() && validateName() && validatePass()) {
		 return true;
	 } else {
		 return false;
	 }
	 	
	 	
 }
 
 function validateEmail() {
	 let emailRegex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
	 
	 if (!emailRegex.test(email.value)) {
		 alert("enter a valid email")
		 return false;
	 } else if (emailRegex.test(email.value)) {
		 return true;
	 }
 }
 
 function validateName() {
	 let nameRegex = /^[a-z ,.'-]+$/i;
	
	 if (!nameRegex.test(fullName.value)) {
		 alert("Please enter your name.")
		 return false;
	 } else if (nameRegex.test(fullName.value)) {
		 return true;
	 }
 }
 
 function validatePass () {
	 let passwordRegex = /^(?=.*\d).{4,8}$/;
	 
	 if (!passwordRegex.test(password.value)) {
		 alert("Password be 4-8 character long and contain a number")
		 return false;
	 } else if (passwordRegex.test(password.value)) {
	 	return true;
	 	}
 }
