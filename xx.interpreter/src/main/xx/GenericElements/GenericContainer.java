package xx.GenericElements;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic container, used for the conversion of container objects in other languages
 * Has a List to represents the elements
 * @author Robin ten Buuren
 *
 */

public class GenericContainer implements GenericAbstractElement {
	
	private List<GenericAbstractElement> elements;
	
	/**
	 * Initializes a GenericContainer using a List of GenericAbstractElements
	 * @param elements the elements of the GenericContainer
	 */
	
	public GenericContainer(List<GenericAbstractElement> elements){
		this.elements = elements;
	}
	
	/**
	 * Initializes a GenericContainer with an empty list
	 */
		
	public GenericContainer(){
		this(new ArrayList<GenericAbstractElement>());
	}
	
	/**
	 * Returns the elements list of the GenericContainer
	 * @return the elements list of the GenericContainer
	 */
	
	public List<GenericAbstractElement> getElements(){
		return this.elements;
	}
	
	/**
	 * Returns an element from the GenericContainer for a specific index
	 * @param index the index of the element to retrieve
	 * @return the element from the GenericContainer at the index
	 */
	
	public GenericAbstractElement get(int index){
		return elements.get(index);
	}
	
	/**
	 * Adds an element to the GenericContainer
	 * @param element the element to be added to the GenericContainer
	 */
	
	public void addElement(GenericAbstractElement element){
		elements.add(element);
	}
	
	/**
	 * Removes an element from the GenericContainer
	 * @param index the index of the element to be removed from the GenericContainer
	 */
	
	public void removeElement(int index){
		elements.remove(index);
	}
	
	/**
	 * Returns the string representation of the GenericContainer
	 * @return the string representation of the GenericContainer
	 */
	
	@Override
	public String toString(){
		String result = "";
		for(int i = 0; i < elements.size(); i++){
			result += "Element " + i + " is: " + elements.get(i).toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Method to clone this GenericContainer
	 * @return a clone of this GenericContainer
	 */
	
	@Override
	public GenericContainer clone(){
		GenericContainer clone = new GenericContainer();
		for(GenericAbstractElement element : elements){
			clone.addElement(element.clone());
		}
		return clone;
	}
	
	/**
	 * Method to calculate the hashCode of this GenericContainer
	 * @return the hashCode of this GenericContainer
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericContainers
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericContainer
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericContainer other = (GenericContainer) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		return true;
	}

}