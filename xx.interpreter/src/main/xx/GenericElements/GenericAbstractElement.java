package xx.GenericElements;

/**
 * Interface used to generalize all the generic common elements
 * @author Robin ten Buuren
 *
 */

public interface GenericAbstractElement {
	
	/**
	 * Abstract method to return a String representation of the GenericAbstractElements
	 * @return a String representation of the GenericAbstractElements
	 */
	
	public abstract String toString();
	
	/**
	 * Abstract method to clone GenericAbstractElements
	 * @return a clone of this GenericAbstractElements
	 */
	
	public abstract GenericAbstractElement clone();
	
	/**
	 * Abstract method that calculates the hashCode of GenericAbstractElements
	 * @return the hashCode of this GenericAbstractElements
	 */
	
	public abstract int hashCode();
	
	/**
	 * Abstract method that compares two GenericAbstractElements
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericAbstractElement
	 */
	
	public abstract boolean equals(Object obj);

}
