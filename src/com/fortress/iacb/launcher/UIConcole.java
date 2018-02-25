package com.fortress.iacb.launcher;

import com.fortress.iacb.services.IBAppUserBusinessLogic;
import com.fortress.iacb.services.IBApplicationUserADOCRUD;

public class UIConcole {

	public static void main(String[] args) {
		
		IBApplicationUserADOCRUD ibUserCRUD = new IBApplicationUserADOCRUD();
		IBAppUserBusinessLogic ibUserLogic = new IBAppUserBusinessLogic();
		
		int state = 0 ;
		if (ibUserLogic.IsApplicationHavingUserRecords() == false) {
			//state = ibUserCRUD.createDefaultApplicationUser();
		}
		// 1. Application User Authentication
		
		/* 1(b) When the application runs the first time, it will check if there
		* users records in the system before it continues. If it find no Users records, then it will
		* call a method to create a default User account before presenting the login screen
		*/
		
		/* 1(b)Accept User login details
		 * Here the application will take user login details
		 */ 
		
		/* 1(c) Perform checks if the Username and Password taken
		* from user is correct
		*/
		
		/* 2 Present the logged-in User with the application menu if
		* 1(a-c) are valid
		*/
		
		//2(a) Create Identity record
		
		//2(b) Read/Search Identity record
		
		//2(c) Update Identity record
		
		//2(d) Delete Identity record
		
	}

}
