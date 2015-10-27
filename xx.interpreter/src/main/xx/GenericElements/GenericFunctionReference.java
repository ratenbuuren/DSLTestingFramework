package xx.GenericElements;

import java.util.List;

/**
 * Generic function reference, used for the conversion of function references in other languages
 * Has a function and a list of parameters
 * @author Robin ten Buuren
 *
 */

public class GenericFunctionReference implements GenericAbstractElement{
	
	private GenericFunction function;
	private List<GenericAbstractElement> parameters;
	
	/**
	 * Initializes a GenericFunctionReference using a GenericFunction and list of parameters
	 * @param function the GenericFunction
	 * @param parameters the list of parameters
	 */
	
	public GenericFunctionReference(GenericFunction function, List<GenericAbstractElement> parameters){
		this.function = function;
		this.parameters = parameters;
	}
	
	/**
	 * Returns the function of the GenericFunctionReference
	 * @return the function of the GenericFunctionReference
	 */

	public GenericFunction getFunction(){
		return this.function;
	}
	
	/**
	 * Returns the parameter list of the GenericFunctionReference
	 * @return the parameter list of the GenericFunctionReference
	 */
	
	public List<GenericAbstractElement> getParameters(){
		return this.parameters;
	}
	
	/**
	 * Returns the string representation of the GenericFunctionReference
	 * @return the string representation of the GenericFunctionReference
	 */
	
	@Override
	public String toString(){
		return function.getName() + " reference with parameters: " + parameters;
	}
	
	/**
	 * Method to clone this GenericFunctionReference
	 * @return a clone of this GenericFunctionReference
	 */

	@Override
	public GenericFunctionReference clone(){
		return new GenericFunctionReference(function, parameters);
	}
	
	/**
	 * Method to calculate the hashCode of this GenericFunctionReference
	 * @return the hashCode of this GenericFunctionReference
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericFunctionReferences
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericFunctionReference
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericFunctionReference other = (GenericFunctionReference) obj;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
}