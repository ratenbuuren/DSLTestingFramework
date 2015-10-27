package xx.GenericElements;

/**
 * Generic String, used for the conversion of String objects in other languages
 * Has a String to represents the value
 * @author Robin ten Buuren
 *
 */

public class GenericString implements GenericAbstractElement {
	
	private String value;
	
	/**
	 * Initializes a GenericString using a String value
	 * @param value the value of the GenericString
	 */
	
	public GenericString(String value){
		this.value = value;
	}
	
	/**
	 * Returns the value of the GenericString
	 * @return the value of the GenericString
	 */
	
	public String getValue(){
		return this.value;
	}
	
	/**
	 * Sets the value of the GenericString
	 * @param value the new value of GenericString
	 */
	
	public void setValue(String value){
		this.value = value;
	}
	
	/**
	 * Returns the string representation of the GenericString
	 * @return the string representation of the GenericString
	 */

	@Override
	public String toString(){
		return "String: " + value;
	}
	
	/**
	 * Method to clone this GenericString
	 * @return a clone of this GenericString
	 */

	@Override
	public GenericString clone(){
		return new GenericString(value);
	}

	/**
	 * Method to calculate the hashCode of this GenericString
	 * @return the hashCode of this GenericString
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericStrings
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericString
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericString other = (GenericString) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
