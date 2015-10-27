package xx.Generators;

import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericConditional;
import xx.GenericElements.GenericIfConditional;

/**
 * Class used to generate the String representation for a JBehave story given a list of generic cases
 * @author Robin ten Buuren
 *
 */

public class PrecedenceStoryGenerator {
	
	//Used to generate String representation and evaluate elements
	private GenericOptions options = new GenericOptions();
	
	/**
	 * Generates the String representation for a JBehave story given a list of cases
	 * This is done by traversing the list of cases and creating a scenario by transforming
	 * the new preconditions to When statements and postconditions to Then statements for each case 
	 * @param cases the list of generic cases
	 * @return the String representation of the JBehave story
	 */
	
	public String generateStory(List<GenericCase> cases){
		System.out.println("GenericStory Cases: " + cases);
		String result = "";
		int caseCounter = 1;
		//Traverse the cases
		for(GenericCase currentCase : cases){
			String caseResult = "Scenario: case " + caseCounter + "\n";
			//Add preconditions
			for(GenericAbstractElement precondition : currentCase.getPreconditions()){
				//Identify type of GenericAbstractElement
				if(precondition instanceof GenericAbstractVariable){
					GenericAbstractVariable variable = (GenericAbstractVariable)precondition;
					caseResult += "When variable " + variable.getName() + " gets value " + options.evaluateElement(variable.getValue()) + "\n";
				}
				else{
					caseResult += "Encountered unsupported precondition type in GenericStoryGenerator";
				}
			}
			caseResult += "\n";
			//Add postconditions
			for(GenericAbstractElement postcondition : currentCase.getPostconditions()){
				//Identify type of GenericAbstractElement
				if(postcondition instanceof GenericAbstractVariable){
					GenericAbstractVariable variable = (GenericAbstractVariable)postcondition;
					caseResult += "Then variable " + variable.getName() + " should return value " + options.evaluateElement(variable.getValue()) + "\n";
				}
				else if(postcondition instanceof GenericConditional){
					if(postcondition instanceof GenericIfConditional){
						GenericIfConditional postIfConditional = (GenericIfConditional)postcondition;
						caseResult += "Then function " + postIfConditional.getName() + " should return value " + options.evaluateElement(postIfConditional) + "\n";
					}
					else{
						caseResult += "Encountered unsupported GenericConditional while adding postconditions";
					}
				}
				else{
					caseResult += "Encountered unsupported postcondition type";
				}
			}
			caseResult += "\n";
			//Update case counter
			caseCounter++;
			//Add case result to final result
			result += caseResult;
		}
		return result;
	}
}
