package xx.GenericElements;

/**
 * Generic parenthesized element, used for the conversion of parenthesized elements in other languages
 * Has an element to represent the element inside the parenthesis
 * @author Robin ten Buuren
 *
 */
public class GenericParenthesizedElement implements GenericAbstractElement{
	
	private GenericAbstractElement element;
	
	/**
	 * Initializes a GenericParenthesizedElement using an element
	 * @param element the element
	 */
	
	public GenericParenthesizedElement(GenericAbstractElement element){
		this.element = element;
	}
	
	/**
	 * Returns the element of the GenericParenthesizedElement
	 * @return the element of the GenericParenthesizedElement
	 */
	
	public GenericAbstractElement getElement(){
		return element;
	}
	
	/**
	 * Returns the string representation of the GenericParenthesizedElement
	 * @return the string representation of the GenericParenthesizedElement
	 */

	@Override
	public String toString(){
		return "(" + element.toString() + ")";
	}
	
	/**
	 * Method to clone this GenericParenthesizedElement
	 * @return a clone of this GenericParenthesizedElement
	 */

	@Override
	public GenericParenthesizedElement clone(){
		return new GenericParenthesizedElement(element.clone());
	}
	
	/**
	 * Method to calculate the hashCode of this GenericParenthesizedElement
	 * @return the hashCode of this GenericParenthesizedElement
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericParenthesizedElements
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericParenthesizedElement
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericParenthesizedElement other = (GenericParenthesizedElement) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}
}