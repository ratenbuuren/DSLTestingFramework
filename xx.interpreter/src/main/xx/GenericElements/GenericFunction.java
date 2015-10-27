package xx.GenericElements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic function, used for the conversion of functions in other languages
 * Has a name and map of input/output values
 * @author Robin ten Buuren
 *
 */

public class GenericFunction implements GenericAbstractElement {

	private String name;
	private Map<List<GenericAbstractElement>, GenericAbstractElement> valueMap = new HashMap<List<GenericAbstractElement>, GenericAbstractElement>();
	
	/**
	 * Initializes a GenericFunction using a name
	 * @param name the name
	 */
	public GenericFunction(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the GenericFunction
	 * @return the name of the GenericFunction
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the input/output map of the GenericFunction
	 * @return the input/output map of the GenericFunction
	 */
	
	public Map<List<GenericAbstractElement>, GenericAbstractElement> getValueMap(){
		return valueMap;
	}
	
	/**
	 * Sets a new input/output map for the GenericFunction
	 * @param newMap the new input/output map
	 */
	
	public void setMap(Map<List<GenericAbstractElement>, GenericAbstractElement> newMap){
		this.valueMap = newMap;
	}
	
	/**
	 * Adds an entry to the input/output map
	 * @param parameters the input parameters
	 * @param output the output parameters
	 */
	
	public void insertEntry(List<GenericAbstractElement> parameters, GenericAbstractElement output){
		valueMap.put(parameters, output);
	}
	
	/**
	 * Removes an entry of the input/output map
	 * @param parameters the input parameters
	 */
	
	public void removeEntry(List<GenericAbstractElement> parameters){
		valueMap.remove(parameters);
	}
	
	/**
	 * Returns a output value using the input parameters as keys in the input/output map
	 * @param parameters the key for the map search
	 * @return the value in the map
	 */
	
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters){
		for(Entry<List<GenericAbstractElement>, GenericAbstractElement> entry : valueMap.entrySet()){
			List<GenericAbstractElement> entryParameters = entry.getKey();
			if(entryParameters.equals(parameters)){
				return entry.getValue();
			}
		}
		return new GenericNull();
	}
	
	/**
	 * Returns the string representation of the GenericFunction
	 * @return the string representation of the GenericFunction
	 */
	
	@Override
	public String toString(){
		return "Function " + name + " with value map:\n " + valueMap + "\n";
	}
	
	/**
	 * Method to clone this GenericFunction
	 * @return a clone of this GenericFunction
	 */
	
	@Override
	public GenericFunction clone() {
		GenericFunction clone = new GenericFunction(name);
		clone.setMap(valueMap);
		return clone;
	}
	
	/**
	 * Method to calculate the hashCode of this GenericFunction
	 * @return the hashCode of this GenericFunction
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((valueMap == null) ? 0 : valueMap.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericFunctions
	 * @param o the object to be compared
	 * @return whether the parameter object is equal to this GenericFunction
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericFunction other = (GenericFunction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (valueMap == null) {
			if (other.valueMap != null)
				return false;
		} else if (!valueMap.equals(other.valueMap))
			return false;
		return true;
	}
	
	

}
