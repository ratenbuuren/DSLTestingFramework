package xx.GenericElements;

/**
 * Generic Boolean, used for the conversion of Boolean objects in other languages
 * Has a boolean to represents the value
 * @author Robin ten Buuren
 *
 */

public class GenericBoolean implements GenericAbstractElement {
	
	private boolean value;
	
	/**
	 * Initializes a GenericBoolean using a boolean value
	 * @param value the value of the GenericBoolean
	 */
	
	public GenericBoolean(boolean value){
		this.value = value;
	}
	
	/**
	 * Returns the value of the GenericBoolean
	 * @return the value of the GenericBoolean
	 */
	
	public boolean getValue(){
		return this.value;
	}
	
	/**
	 * Sets the value of the GenericBoolean
	 * @param value the new value of GenericBoolean
	 */
	
	public void setValue(boolean value){
		this.value = value;
	}
	
	/**
	 * Returns the string representation of the GenericBoolean
	 * @return the string representation of the GenericBoolean
	 */
	
	@Override
	public String toString(){
		return "Boolean: " + value;
	}
	
	/**
	 * Method to clone this GenericBoolean
	 * @return a clone of this GenericBoolean
	 */
	
	@Override
	public GenericBoolean clone(){
		return new GenericBoolean(value);
	}
	
	/**
	 * Method to calculate the hashCode of this GenericBoolean
	 * @return the hashCode of this GenericBoolean
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (value ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Method to compare two GenericBooleans
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericBoolean
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericBoolean other = (GenericBoolean) obj;
		if (value != other.value)
			return false;
		return true;
	}

}
