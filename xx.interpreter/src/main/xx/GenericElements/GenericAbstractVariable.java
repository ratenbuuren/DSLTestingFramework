package xx.GenericElements;

/**
 * Generic abstract variable, used for the conversion of variables in other languages
 * Has a name and several abstract methods to be implemented by the specific variables types
 * @author Robin ten Buuren
 *
 */

public abstract class GenericAbstractVariable implements GenericAbstractElement{
	
	private String name;
	
	/**
	 * Initializes a GenericAbstractVariable using a name
	 * @param name the name of the GenericAbstractVariable
	 */
	
	public GenericAbstractVariable(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the GenericAbstractVariable
	 * @return the name of the GenericAbstractVariable
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Abstract method that returns the value of the GenericAbstractVariable
	 * @return the value of the GenericAbstractVariable
	 */
	
	public abstract GenericAbstractElement getValue();
	
	/**
	 * Abstract method that returns the value of the GenericAbstractVariable for a specific index
	 * @param index the index
	 * @return the value of the GenericAbstractVariable at the index
	 */
	
	public abstract GenericAbstractElement getValue(int index);
	
	/**
	 * Abstract method that sets the value of the GenericAbstractVariable
	 * @param value the new value of this GenericAbstractVariable
	 */
	
	public abstract void setValue(GenericAbstractElement value);
	
	/**
	 * Abstract method that returns the string representation of the GenericAbstractVariable
	 * @return the string representation of the GenericAbstractVariable
	 */

	@Override
	public abstract String toString();
	
	/**
	 * Abstract method to clone this GenericAbstractVariable
	 * @return a clone of this GenericAbstractVariable
	 */

	@Override
	public abstract GenericAbstractVariable clone();
	
	/**
	 * Abstract method to calculate the hashCode of this GenericAbstractVariable
	 * @return the hashCode of this GenericAbstractVariable
	 */

	@Override
	public abstract int hashCode();
	
	/**
	 * Abstract method to compare two GenericAbstractVariables
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericAbstractVariable
	 */

	@Override
	public abstract boolean equals(Object obj);
}