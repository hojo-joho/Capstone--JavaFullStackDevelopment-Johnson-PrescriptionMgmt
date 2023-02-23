	ReadME!

	-- Prescription Management --

	Description:
	
		Users are able to register for app and log in with their credentials to view their personal list of medications.
		The list includes the name of the medication, instuctions for use, refill date, and how long the supply is supposed to last.  
		Users can make admins aware of any issues they are having either with the medication or with the app itself.
		They are able to request refills in app for a pharmacist to verify. 

	Usage:
	
		Register and Log in to view homepage.
		Users are automatically assigned a pharmacy and a role "USER"
		This is the base role that allows access to managing personal medication list.
		Admin users can only be created by changing the role to "ADMIN" in the User table in the database. 
		This is intentional to avoid any sensitive information accidently being leaked to a user that shouldn't have higher privledges. 
		Admin users have access to view all medications that need refills, 
		App currenly only supports one pharmacy but can be expanded to include more. 


	Features coming in the future:
	
		Multiple pharmacies for users to choose from when registering. 
		More detail informaion reguarding medication.
		Additional model for medication info like side effects.
		ADMIN capabilities for pharmacist to sync multiple refills for user to minimize trips to the pharmacy.


	Hillary Johnson
