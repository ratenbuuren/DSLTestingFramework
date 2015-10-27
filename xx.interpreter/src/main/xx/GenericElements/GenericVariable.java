package xx.GenericElements;

/**
 * Generic variable, used for the conversion of variables in other languages
 * Extends GenericAbstractVariable and has a name and value
 * @author Robin ten Buuren
 *
 */

public class GenericVariable extends GenericAbstractVariable{
	
	private GenericAbstractElement value;
	
	/**
	 * Initializes a GenericVariable by calling GenericAbstractVariable using a name and initializes its value
	 * @param name the name
	 * @param value the value
	 */
	
	public GenericVariable(String name, GenericAbstractElement value){
		super(name);
		this.value = value;
	}
	
	/**
	 * Initializes a GenericVariable with a name and value GenericNull
	 * @param name the name
	 */
	
	public GenericVariable(String name){
		this(name, new GenericNull());
	}
	
	/**
	 * Returns the value of the GenericVariable
	 * @return the value of the GenericVariable
	 */
	
	@Override
	public GenericAbstractElement getValue(){
		return this.value;
	}
	
	/**
	 * Returns the value of the GenericVariable at a specific index
	 * @param index the index of the element
	 * @return the value of the GenericVariable at the index
	 */
	
	@Override
	public GenericAbstractElement getValue(int index){
		return this.value;
	}
	
	/**
	 * Sets the value of the GenericVariable
	 * @param value the new value of this GenericVariable
	 */
	
	@Override
	public void setValue(GenericAbstractElement value){
		this.value = value;
	}
	
	/**
	 * Returns the string representation of the GenericVariable
	 * @return the string representation of the GenericVariable
	 */

	@Override
	public String toString(){
		return "Variable " + super.getName() + "[" + value.toString() + "]";
	}
	
	/**
	 * Method to clone this GenericVariable
	 * @return a clone of this GenericVariable
	 */

	@Override
	public GenericVariable clone(){
		return new GenericVariable(super.getName(), value.clone());
	}
	
	/**
	 * Method to calculate the hashCode of this GenericVariable
	 * @return the hashCode of this GenericVariable
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getName() == null) ? 0 : super.getName().hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericVariables
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericVariable
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericVariable other = (GenericVariable) obj;
		if (super.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!super.getName().equals(other.getName()))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}