package xx.GenericElements;

/**
 * Generic abstract variable reference, used for the conversion of variable references in other languages
 * Has a GenericAbstractVariable to represent the variable
 * @author Robin ten Buuren
 *
 */

public class GenericAbstractVariableReference implements GenericAbstractElement{
	
	private GenericAbstractVariable variable;
	
	/**
	 * Initializes a GenericAbstractVariableReference using a GenericAbstractVariable
	 * @param variable the GenericAbstractVariable of the GenericAbstractVariableReference
	 */
	
	public GenericAbstractVariableReference(GenericAbstractVariable variable){
		this.variable = variable;
	}
	
	/**
	 * Returns the variable of the GenericAbstractVariableReference
	 * @return the variable of the GenericAbstractVariableReference
	 */
	
	public GenericAbstractVariable getVariable(){
		return this.variable;
	}
	
	/**
	 * Returns the string representation of the GenericAbstractVariableReference
	 * @return the string representation of the GenericAbstractVariableReference
	 */

	@Override
	public String toString(){
		return "VariableRef: " + variable;
	}
	
	/**
	 * Method to clone this GenericAbstractVariableReference
	 * @return a clone of this GenericAbstractVariableReference
	 */

	@Override
	public GenericAbstractVariableReference clone(){
		return new GenericAbstractVariableReference(variable.clone());
	}
	
	/**
	 * Method to calculate the hashCode of this GenericAbstractVariableReference
	 * @return the hashCode of this GenericAbstractVariableReference
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericAbstractVariableReferences
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericAbstractVariableReference
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericAbstractVariableReference other = (GenericAbstractVariableReference) obj;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

}