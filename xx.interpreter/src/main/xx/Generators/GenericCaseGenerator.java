package xx.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericIfConditional;

/**
 * Class used to return the cases for a list of elements and a map of variables and values.
 * The tests developed with these cases should result in branch/condition coverage
 * @author Robin ten Buuren
 *
 */

public class GenericCaseGenerator {
	
	//The resulting list of generic cases
	private List<GenericCase> result = new ArrayList<GenericCase>();
	
	//The current case
	private GenericCase currentCase = new GenericCase(); 
	
	/**
	 * Generates the cases for a list of elements using a map of variable values
	 * @param elements the elements as basis for the cases
	 * @param variableValues the variables and their values
	 * @return a list of generic cases
	 */
	
	public List<GenericCase> generateCases(List<GenericAbstractElement> elements, Map<GenericAbstractElement, List<GenericAbstractElement>> variableValues){
		return generateCases(elements, variableValues, new GenericCase());
	}
	
	/**
	 * Generates the cases for a list of elements using a map of variable values and an existing case
	 * This is achieved by calling generateCase for each value of a variable while removing that variable from the variable values map.
	 * @param elements The elements used as the base for the cases
	 * @param variableValues map of variables and possible values, used to calculate the possible element cases
	 * @param genCase the base generic case
	 */
	
	public List<GenericCase> generateCases(List<GenericAbstractElement> elements, Map<GenericAbstractElement, List<GenericAbstractElement>> variableValues, GenericCase genCase){
		if(!variableValues.isEmpty()){
			//Take the first variable from the list and create a new list without that variable
			GenericAbstractElement firstKey = variableValues.keySet().iterator().next();
			Map<GenericAbstractElement, List<GenericAbstractElement>> newMap = new HashMap<GenericAbstractElement, List<GenericAbstractElement>>();
			newMap.putAll(variableValues);
			newMap.remove(firstKey);
			
			//Traverse the values of the first variable
			for(GenericAbstractElement value : variableValues.get(firstKey)){
				//For each value, create a clone of the elements and call generateCase
				List<GenericAbstractElement> newElements = new ArrayList<GenericAbstractElement>();
				for(GenericAbstractElement element: elements){
					newElements.add(element.clone());
				}
				generateCase(newElements, newMap, firstKey, value, genCase);
			}
		}
		return result;
	}
	
	/**
	 * Generates a case for a list of elements using a variable, a value, and a base case 
	 * @param elements the base elements
	 * @param variableValues used to recursively call generateCases
	 * @param variable the variable to be replaced
	 * @param value the value to replace the variable
	 * @param genCase the base case
	 */
	
	public void generateCase(List<GenericAbstractElement> elements, Map<GenericAbstractElement, List<GenericAbstractElement>> variableValues,
										GenericAbstractElement variable, GenericAbstractElement value, GenericCase genCase){
		//Clear the current case
		currentCase.clear();

		//Replace the variable with the value
		List<GenericAbstractElement> replacedElements = replaceElementForList(elements, variable, value);
		
		//Update the current case with the preconditions of the base case		
		for(GenericAbstractElement pre : genCase.getPreconditions()){
			currentCase.addPrecondition(pre);
		}
		//Add the changed variable with its value to the preconditions
		if(variable instanceof GenericAbstractVariable){
			GenericAbstractVariable preVariable = (GenericAbstractVariable) variable.clone();
			preVariable.setValue(value);
			currentCase.addPrecondition(preVariable);
		}
		else{
			System.out.println("Unknown element in generateCase GenericCaseGenerator: " + variable);
		}
		//If there are not more unused variable values left
		if(variableValues.isEmpty()){
			//Add the postconditions
			addElements(replacedElements);
			//Add a clone of the case to the result
			result.add(currentCase.clone());
		}
		else{
			//Call generateCases with the current case as base case
			result.addAll(new GenericCaseGenerator().generateCases(replacedElements, variableValues, currentCase));
		}
	}
	
	/**
	 * Iterates over a list and calls assignVariable for every element of the list, thereby assigning the variable to each variable occurrence
	 * @param elements the list of elements
	 * @param variable the variable to be assigned
	 * @param value the new value for the variable
	 * @return a list of element where the value is assigned to the variable
	 */
	
	public List<GenericAbstractElement> replaceElementForList(List<GenericAbstractElement> elements, GenericAbstractElement variable, GenericAbstractElement value){
		List<GenericAbstractElement> result = new ArrayList<GenericAbstractElement>();
		for(GenericAbstractElement element : elements){
			//Only variables can be assigned
			if(variable instanceof GenericAbstractVariable){
				GenericAbstractVariable var = (GenericAbstractVariable)variable;
				result.add(new GenericVariableAssigner().assignVariable(element, var.getName(), value));
			}
			
		}
		return result;
	}
	
	/**
	 * Iterates over a list and adds a postcondition to the result if the element in the list is a (new) variable or function
	 * @param elements the list of elements
	 */
	
	public void addElements(List<GenericAbstractElement> elements){
		//List to contain the known variables
		List<String> knownVariables = new ArrayList<String>();
		for(GenericAbstractElement element : elements){
			if(element instanceof GenericAbstractVariable){
				GenericAbstractVariable variable = (GenericAbstractVariable) element;
				//The variable should not be known (avoids duplicates)
				if(!knownVariables.contains(variable.getName())){
					knownVariables.add(variable.getName());
					currentCase.addPostcondition(element);
				}
			}
			if(element instanceof GenericIfConditional){
				currentCase.addPostcondition(element);
			}
		}
	}
}
