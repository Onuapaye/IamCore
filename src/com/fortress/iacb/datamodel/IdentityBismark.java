package com.fortress.iacb.datamodel;

/**
 * <h3>The Identity Class IdentityBismark</h3>
 * <p></p>
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismark {
	
	private String firstName;
	private String lastName;
	private String displayName;
	private String uniqueId;
	private String email;
	
	/**
	 * A constructor of the default class IdentityBismark in order to be able to 
	 * assign value to the variables since their exposure/access levels are set as private
	 * This constructor has empty statement within it
	 */
	public IdentityBismark() {
		
	}
	
	/**
	 * This is an overload version of the constructor to IdentityBismark class.
	 * It has series of parameter within its call and the values passed to the parameters
	 * will then be assigned to the properties of the IdentityBismark class which has been indicated
	 * within the constructor.
	 * @param firstName
	 * @param lastName
	 * @param displayName
	 * @param uniqueId
	 * @param email
	 */
	public IdentityBismark(String firstName, String lastName, String displayName, String uniqueId, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.uniqueId = uniqueId;
		this.email = email;
	}
	
	/**
	 * This method gets the firstName  field variable value set via the setFirstName() method
	 * and returns it as a String
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This method helps to take or accept value entered via its parameter
	 * firstName as String and assigns the parameter value to the field variable firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * This method gets the lastName field variable value set via the setLastName() method
	 * and returns it as a String
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * This method helps to take or accept value entered via its parameter
	 * lastName as String and assigns the parameter value to the field variable lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * This method gets the displayName field variable value set via the setDisplayName() method
	 * and returns it as a String
	 * @return displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * This method helps to take or accept values entered via its parameters
	 * firstName and lastName as String and assigns the parameter values to the 
	 * field variables firstName and lastName
	 * @param firstName, lastName
	 */
	public void setDisplayName(String firstName, String lastName) {
		this.displayName = firstName + " " + lastName.toUpperCase();
	}
	
	/**
	 * This method gets the uniqueId field variable value set via the setUniqueId() method
	 * and returns it as a String
	 * @return uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	
	/**
	 * This method helps to take or accept value entered via its parameter
	 * uniqueId as String and assigns the parameter values to the 
	 * field variables uniqueId
	 * @param uniqueId
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	/**
	 * This method gets the email field variable value set via the setEmail() method
	 * and returns it as a String
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This method helps to take or accept a value entered via its parameter
	 * email as String and assigns the parameter value to the 
	 * field variable email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

	/**
	 * This method overrides the default toString() method of the IdentityBismark class.
	 * Overriding is used to provide the  implementation toString() method that is 
	 * already provided by the super class
	 */
	@Override
	public String toString() {
		return "Identity Details Are: [Display Name = " + this.displayName + 
				" Unique ID = " + this.uniqueId + " E-mail = " + this.email + " ]";
	}
	
	/**
	 * <h4>Algorithm for Computing hashCode()</h4>
	 *  This method overrides the default class hashCode() method and computes a field’s 
	 *  hash code by calling hashCode() on it. E.g. firstName.hashCode() 
	 *  
	 *  A common algorithm is to start with some arbitrary number (usually a small prime) 
	 *  and to repeatedly multiply it with another before adding a field’s hash value
	 */
	@Override
	public int hashCode() {
		int iacbPrime = 31;
		int iacbResults = 1;
		
		iacbResults = iacbPrime * iacbResults + ((firstName == null) ? 0 : firstName.hashCode()); 
		iacbResults = iacbPrime * iacbResults + ((lastName == null) ? 0 : lastName.hashCode());
		iacbResults = iacbPrime * iacbResults + ((displayName == null) ? 0 : displayName.hashCode());
		iacbResults = iacbPrime * iacbResults + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		iacbResults = iacbPrime * iacbResults + ((email == null) ? 0 : email.hashCode());
		
		return iacbResults;
	}
	
	/**
	 *  This method checks if some other Object passed to it as an argument is equal to 
	 *  the Object on which it is invoked.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		
		if(object == null) {
			return false;
		}
		
		if(getClass() != object.getClass()) {
			return false;
		}
		
		final IdentityBismark iBismark = (IdentityBismark) object;
		
		//first name
		if(firstName == null) {
			if(iBismark.firstName != null) {
				return false;
			}
		} else if(!firstName.equals(iBismark.firstName)) {
			return false;
		}
		
		//last name
		if(lastName == null) {
			if(iBismark.lastName != null) {
				return false;
			}
		} else if(!lastName.equals(iBismark.lastName)) {
			return false;
		}
		
		//unique id
		if(uniqueId == null) {
			if(iBismark.uniqueId != null) {
				return false;
			}
		} else if(!uniqueId.equals(iBismark.uniqueId)) {
			return false;
		}
		
		//email
		if(email == null) {
			if(iBismark.email != null) {
				return false;
			}
		} else if(!email.equals(iBismark.email)) {
			return false;
		}
		
		return true;
	}
	
}
