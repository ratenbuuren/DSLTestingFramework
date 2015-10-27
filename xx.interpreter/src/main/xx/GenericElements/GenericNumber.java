package xx.GenericElements;

/**
 * Generic number, used for the conversion of number objects in other languages
 * Has a double to represents the value
 * @author Robin ten Buuren
 *
 */

public class GenericNumber implements GenericAbstractElement {
	
	private double value;
	
	/**
	 * Initializes a GenericNumber using a double value
	 * @param value the value of the GenericNumber
	 */
	
	public GenericNumber(double value){
		this.value = value;
	}
	
	/**
	 * Returns the value of the GenericNumber
	 * @return the value of the GenericNumber
	 */
	
	public double getValue(){
		return this.value;
	}
	
	/**
	 * Sets the value of the GenericNumber
	 * @param value the new value of GenericNumber
	 */
	
	public void setValue(double value){
		this.value = value;
	}
	
	/**
	 * Returns the string representation of the GenericNumber
	 * @return the string representation of the GenericNumber
	 */

	@Override
	public String toString(){
		return "" + value;
	}
	
	/**
	 * Method to clone this GenericNumber
	 * @return a clone of this GenericNumber
	 */

	@Override
	public GenericNumber clone(){
		return new GenericNumber(value);
	}
	
	/**
	 * Method to calculate the hashCode of this GenericNumber
	 * @return the hashCode of this GenericNumber
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
 	/**
	 * Method to compare two GenericNumbers
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericNumber
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericNumber other = (GenericNumber) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}
}
