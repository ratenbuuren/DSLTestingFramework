package xx.GenericElements;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic list variable, used for the conversion of list variables in other languages
 * Extends GenericAbstractVariable and has a name, default value and list of values
 * @author Robin ten Buuren
 *
 */

public class GenericListVariable extends GenericAbstractVariable{

	private GenericAbstractElement defaultValue;
	private List<GenericAbstractElement> values;
	
	/**
	 * Initializes a GenericListVariable by calling GenericAbstractVariable using a name and initializes its default value and start length
	 * @param name the name
	 * @param defaultValue the default value
	 * @param startLength the start length of the list
	 */
	
	public GenericListVariable(String name, GenericAbstractElement defaultValue, int startLength){
		super(name);
		this.defaultValue = defaultValue;
		this.values = new ArrayList<GenericAbstractElement>();
		for(int i = 0; i < startLength; i++){
			values.add(defaultValue);
		}
	}
	
	/**
	 * Initializes a empty GenericListVariable using a name, default value GenericNull and length zero 
	 * @param name the name
	 * @param length the length of the list
	 */
	
	public GenericListVariable(String name){
		this(name, new GenericNull(), 0);
	}
	
	/**
	 * Returns the default value of the GenericListVariable
	 * @return the default value of the GenericListVariable
	 */

	@Override
	public GenericAbstractElement getValue() {
		return defaultValue;
	}
	
	/**
	 * Returns the values list of the GenericListVariable
	 * @return the values list of the GenericListVariable
	 */

	public List<GenericAbstractElement> getValues() {
		return values;
	}
	
	/**
	 * Returns a value from the GenericListVariable for a specific index
	 * @param index the index of the element to retrieve
	 * @return the value in the GenericListVariable at the index
	 */
	
	public GenericAbstractElement getValue(int index){
		return values.get(index);
	}
	
	/**
	 * Sets a new default value by replacing all the old default values with the parameter
	 * @param newValue the new default value
	 */
	
	@Override
	public void setValue(GenericAbstractElement newValue){
		for(int i = 0; i < values.size(); i++){
			if(values.get(i).equals(defaultValue)){
				values.set(i, newValue);
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
		values.set(index, newValue);
	}
	
	/**
	 * Adds an element to the list of values
	 * @param element the element to be added to the list
	 */
	
	public void addElement(GenericAbstractElement element){
		values.add(element);
	}
	
	/**
	 * Removes an element of the list of values
	 * @param index the index of the element to be removed
	 */
	
	public void removeElement(int index){
		values.remove(index);
	}

	/**
	 * Returns the string representation of the GenericListVariable
	 * @return the string representation of the GenericListVariable
	 */
	
	@Override
	public String toString(){
		String result = "ListVariable " + super.getName() + " default: " + defaultValue + "[";
		for(int i = 0; i < values.size(); i++){
			if(values.get(i) != null && !(values.get(i).equals(defaultValue))){
				result += "Element " + i + " is: " + values.get(i);
			}
		}
		result += "]";
		return result;
	}
	
	/**
	 * Method to clone this GenericListVariable
	 * @return a clone of this GenericListVariable
	 */

	@Override
	public GenericListVariable clone(){
		GenericListVariable newVariable = new GenericListVariable(super.getName(), defaultValue.clone(), values.size());
		for(int i = 0; i < values.size(); i++){
			newVariable.addElement(values.get(i).clone());
		}
		return newVariable;
	}
	
	/**
	 * Method to calculate the hashCode of this GenericListVariable
	 * @return the hashCode of this GenericListVariable
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericListVariables
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericListVariable
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericListVariable other = (GenericListVariable) obj;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}