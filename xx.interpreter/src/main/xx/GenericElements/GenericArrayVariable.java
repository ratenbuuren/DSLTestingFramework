package xx.GenericElements;

import java.util.Arrays;

/**
 * Generic array variable, used for the conversion of array variables in other languages
 * Extends GenericAbstractVariable and has a name, default value and array of values
 * @author Robin ten Buuren
 *
 */

public class GenericArrayVariable extends GenericAbstractVariable{

	private GenericAbstractElement defaultValue;
	private GenericAbstractElement[] values;
	
	/**
	 * Initializes a GenericArrayVariable by calling GenericAbstractVariable using a name and initializes its default value and array length
	 * @param name the name
	 * @param defaultValue the default value
	 * @param length the length of the array
	 */
	
	public GenericArrayVariable(String name, GenericAbstractElement defaultValue, int length){
		super(name);
		this.defaultValue = defaultValue;
		this.values = new GenericAbstractElement[length];
		for(int i = 0; i < length; i++){
			values[i] = defaultValue;
		}
	}
	
	/**
	 * Initializes a empty GenericArrayVariable using a name, default value GenericNull and array length 
	 * @param name the name
	 * @param length the length of the array
	 */
	
	public GenericArrayVariable(String name, int length){
		this(name, new GenericNull(), length);
	}
	
	/**
	 * Returns the default value of the GenericArrayVariable
	 * @return the default value of the GenericArrayVariable
	 */

	@Override
	public GenericAbstractElement getValue() {
		return defaultValue;
	}
	
	/**
	 * Returns the values array of the GenericArrayVariable
	 * @return the values array of the GenericArrayVariable
	 */

	public GenericAbstractElement[] getValues() {
		return values;
	}
	
	/**
	 * Returns the array length of the GenericArrayVariable
	 * @return the array length of the GenericArrayVariable
	 */
	
	public int getLength(){
		return values.length;
	}
	
	/**
	 * Returns a value from the GenericArrayVariable for a specific index
	 * @param index the index of the element to retrieve
	 * @return the value in the GenericArrayVariable at the index
	 */
	
	public GenericAbstractElement getValue(int index){
		return values[index];
	}
	
	/**
	 * Sets a new default value by replacing all the old default values with the parameter
	 * @param newValue the new default value
	 */
	
	@Override
	public void setValue(GenericAbstractElement newValue){
		for(int i = 0; i < values.length; i++){
			if(values[i].equals(defaultValue)){
				values[i] = newValue;
			}
		}
		this.defaultValue = newValue;
	}
	
	/**
	 * Sets a new value for a specific index
	 * @param index the index of the element to set
	 * @param newValue the new value to be set at the index
	 */
	
	public void setValue(int index, GenericAbstractElement newValue){
		values[index] = newValue;
	}
	
	/**
	 * Returns the string representation of the GenericArrayVariable
	 * @return the string representation of the GenericArrayVariable
	 */
	
	@Override
	public String toString(){
		String result = "ArrayVariable " + super.getName() + " default: " + defaultValue + "[";
		for(int i = 0; i < values.length; i++){
			if(values[i] != null && !(values[i].equals(defaultValue))){
				result += "Element " + i + " is: " + values[i];
			}
		}
		result += "]";
		return result;
	}
	
	/**
	 * Method to clone this GenericArrayVariable
	 * @return a clone of this GenericArrayVariable
	 */

	@Override
	public GenericArrayVariable clone(){
		GenericArrayVariable newVariable = new GenericArrayVariable(super.getName(), defaultValue.clone(), values.length);
		for(int i = 0; i < values.length; i++){
			newVariable.setValue(i, values[i].clone());
		}
		return newVariable;
	}
	
	/**
	 * Method to calculate the hashCode of this GenericArrayVariable
	 * @return the hashCode of this GenericArrayVariable
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((super.getName() == null) ? 0 : super.getName().hashCode());
		result = prime * result + Arrays.hashCode(values);
		return result;
	}
	
	/**
	 * Method to compare two GenericArrayVariables
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericArrayVariable
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericArrayVariable other = (GenericArrayVariable) obj;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (super.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!super.getName().equals(other.getName()))
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

}