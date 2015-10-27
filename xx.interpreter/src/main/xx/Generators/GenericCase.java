package xx.Generators;

import java.util.ArrayList;
import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.Generators.GenericCase;

/**
 * Class used to present a generic case for a test
 * Contains two list of GenericAbstractElements, one for preconditions and the other for postconditions 
 * @author Robin ten Buuren
 *
 */
public class GenericCase {
	
	private List<GenericAbstractElement> preconditions;
	private List<GenericAbstractElement> postconditions;
	
	/**
	 * Creates a new GenericCase with an empty list of preconditions and postconditions
	 */
	
	public GenericCase(){
		preconditions = new ArrayList<GenericAbstractElement>();
		postconditions = new ArrayList<GenericAbstractElement>();
	}
	
	/**
	 * Creates a new GenericCase given a list of preconditions and postconditions
	 * @param preconditions the preconditions of the new case
	 * @param postconditions the postconditions of the new case
	 */
	
	public GenericCase(List<GenericAbstractElement> preconditions, List<GenericAbstractElement> postconditions){
		this.preconditions = preconditions;
		this.postconditions = postconditions;
	}
	
	/**
	 * Returns the list of preconditions
	 * @return the list of preconditions
	 */
	
	public List<GenericAbstractElement> getPreconditions(){
		return preconditions;
	}
	
	/**
	 * Returns the list of postconditions
	 * @return the list of postconditions
	 */
	
	public List<GenericAbstractElement> getPostconditions(){
		return postconditions;
	}
	
	/**
	 * Adds a precondition to the case
	 * @param element the precondition to be added
	 */
	
	public void addPrecondition(GenericAbstractElement element){
		preconditions.add(element);
	}
	
	/**
	 * Adds a postcondition to the case
	 * @param element the postcondition to be added
	 */
	
	public void addPostcondition(GenericAbstractElement element){
		postconditions.add(element);
	}
	
	/**
	 * Clears the case of preconditions and postconditions
	 */
	
	public void clear(){
		this.preconditions.clear();
		this.postconditions.clear();
	}
	
	/**
	 * Returns the string representation of the GenericCase
	 * @return the string representation of the GenericCase
	 */

	public String toString(){
		String result = "Preconditions: \n";
		for(GenericAbstractElement element : preconditions){
			result += element.toString() + "\n";
		}
		result += "Postconditions: \n";
		for(GenericAbstractElement element : postconditions){
			result += element.toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Clones the GenericCase and returns a new instance with the same preconditions and postconditions
	 * The elements are not cloned during the process
	 * @return a new instance of the generic case with the same preconditions and postconditions
	 */
	
	public GenericCase clone(){
		GenericCase temp = new GenericCase();
		for(GenericAbstractElement element : preconditions){
			temp.addPrecondition(element);
		}
		for(GenericAbstractElement element : postconditions){
			temp.addPostcondition(element);
		}
		return temp;
	}
}
