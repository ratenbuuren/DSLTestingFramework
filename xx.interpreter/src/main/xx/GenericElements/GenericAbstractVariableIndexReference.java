package xx.GenericElements;

/**
 * Generic abstract variable index reference, used for the conversion of variable index references in other languages
 * Has a GenericAbstractVariable and index to represent the variable and index
 * @author Robin ten Buuren
 *
 */

public class GenericAbstractVariableIndexReference implements GenericAbstractElement{
	
	private GenericAbstractVariable variable;
	private GenericAbstractElement index;
	
	/**
	 * Initializes a GenericAbstractVariableIndexReference using a variable and index
	 * @param variable the variable of the GenericAbstractVariableIndexReference
	 * @param index the index of the GenericAbstractVariableIndexReference
	 */
	
	public GenericAbstractVariableIndexReference(GenericAbstractVariable variable, GenericAbstractElement index){
		this.variable = variable;
		this.index = index;
	}
	
	/**
	 * Returns the variable of the GenericAbstractVariableIndexReference
	 * @return the variable of the GenericAbstractVariableIndexReference
	 */
	
	public GenericAbstractVariable getVariable(){
		return this.variable;
	}
	
	/**
	 * Returns the index of the GenericAbstractVariableIndexReference
	 * @return the index of the GenericAbstractVariableIndexReference
	 */
	
	public GenericAbstractElement getIndex(){
		return this.index;
	}
	
	/**
	 * Returns the string representation of the GenericAbstractVariableIndexReference
	 * @return the string representation of the GenericAbstractVariableIndexReference
	 */
	
	@Override
	public String toString(){
		return "VariableIndexRef: " + variable.getName() + "[" + index.toString() + "]";
	}
	
	/**
	 * Method to clone this GenericAbstractVariableIndexReference
	 * @return a clone of this GenericAbstractVariableIndexReference
	 */
	
	@Override
	public GenericAbstractVariableIndexReference clone(){
		return new GenericAbstractVariableIndexReference(variable, index.clone());
	}
	
	/**
	 * Method to calculate the hashCode of this GenericAbstractVariableIndexReference
	 * @return the hashCode of this GenericAbstractVariableIndexReference
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericAbstractVariableIndexReferences
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericAbstractVariableIndexReference
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericAbstractVariableIndexReference other = (GenericAbstractVariableIndexReference) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}
}