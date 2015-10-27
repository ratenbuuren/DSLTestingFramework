package xx.GenericElements;

/**
 * Generic null, used for the conversion of null instances in other languages
 * @author Robin ten Buuren
 *
 */

public class GenericNull implements GenericAbstractElement {
	
	private String nullString = "GenericNull";
	
	/**
	 * Returns the string representation of the GenericNull
	 * @return the string representation of the GenericNull
	 */

	@Override
	public String toString(){
		return nullString;
	}
	
	/**
	 * Method to clone this GenericNull
	 * @return a clone of this GenericNull
	 */

	@Override
	public GenericNull clone(){
		return new GenericNull();
	}
	
	/**
	 * Method to calculate the hashCode of this GenericNull
	 * @return the hashCode of this GenericNull
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nullString == null) ? 0 : nullString.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericNulls
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericNull
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericNull other = (GenericNull) obj;
		if (nullString == null) {
			if (other.nullString != null)
				return false;
		} else if (!nullString.equals(other.nullString))
			return false;
		return true;
	}

}
