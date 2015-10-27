package xx.Generators;

import java.util.ArrayList;
import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericAbstractVariableIndexReference;
import xx.GenericElements.GenericAbstractVariableReference;
import xx.GenericElements.GenericArrayVariable;
import xx.GenericElements.GenericBinaryExpression;
import xx.GenericElements.GenericBoolean;
import xx.GenericElements.GenericConditional;
import xx.GenericElements.GenericContainer;
import xx.GenericElements.GenericFunction;
import xx.GenericElements.GenericFunctionReference;
import xx.GenericElements.GenericIfConditional;
import xx.GenericElements.GenericListVariable;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericParenthesizedElement;
import xx.GenericElements.GenericString;
import xx.GenericElements.GenericUnaryExpression;
import xx.GenericElements.GenericVariable;
import xx.Generators.GenericOptions;

public class GenericVariableAssigner {
	
	//Name of the variable used for the replacing of a variable value
	private String currentVariableName;
	//Value of the variable used for the replacing of a variable value
	private GenericAbstractElement currentValue;
	
	/**
	 * Traverses the element and assigns a variable a new value
	 * @param element the element with the variable
	 * @param variable the variable to be replaced
	 * @param value the value for the variable 
	 * @return element where the variable is assigned the value
	 */
	
	public GenericAbstractElement assignVariable(GenericAbstractElement element, String variableName, GenericAbstractElement value){
		currentVariableName = variableName;
		currentValue = value;
		return replace(element);
	}
	
	/**
	 * Assigning a variable a new value by switching on element type
	 * @param element base element
	 * @return element where the variable is assigned the value or null if an unsupported element is detected
	 */
	
	public GenericAbstractElement replace(GenericAbstractElement element) {
		if (element instanceof GenericBinaryExpression) {
			return replace((GenericBinaryExpression) element);
		}
		else if (element instanceof GenericUnaryExpression){
			return replace((GenericUnaryExpression) element);
		}
		else if(element instanceof GenericParenthesizedElement){
			return replace((GenericParenthesizedElement) element);
		}
		else if(element instanceof GenericAbstractVariable){
			return replace((GenericAbstractVariable) element);
		}
		else if(element instanceof GenericAbstractVariableIndexReference){
			return replace((GenericAbstractVariableIndexReference) element);
		}
		else if (element instanceof GenericNumber) {
			return element;
		}
		else if(element instanceof GenericString) {
			return element;
		}
		else if(element instanceof GenericBoolean) {
			return element;
		}
		else if(element instanceof GenericNull){
			return element;
		}
		else if(element instanceof GenericContainer){
			return replace((GenericContainer) element);
		}
		else if(element instanceof GenericFunction) {
			return replace((GenericFunction) element);
		}
		else if(element instanceof GenericFunctionReference){
			return replace((GenericFunctionReference) element);
		}
		else if(element instanceof GenericConditional){
			return replace((GenericConditional) element);
		}
		else if(element instanceof GenericAbstractVariableReference){
			return replace((GenericAbstractVariableReference) element);
		}
		else{
			System.err.println("Could not replace element: " + element);
			return null;
		}
	}
	
	/**
	 * Replaces a GenericContainer by calling replace() for each element
	 * @param container the base container
	 * @return GenericContainer with replaced elements
	 */
	
	public GenericContainer replace(GenericContainer container){
		List<GenericAbstractElement> newElements = new ArrayList<GenericAbstractElement>();
		for(GenericAbstractElement element : container.getElements()){
			newElements.add(replace(element));
		}
		return new GenericContainer(newElements);
	}
	
	/**
	 * Replaces a GenericBinaryExpression by replacing the left and right child expressions
	 * @param expression the base binary expression
	 * @return GenericBinaryExpression where the variable is assigned the new value
	 */
	
	public GenericBinaryExpression replace(GenericBinaryExpression expression){
		return new GenericBinaryExpression(replace(expression.getLeft()), replace(expression.getRight()), expression.getOperator());
	}
	
	/**
	 * Replaces a GenericUnaryExpression by replacing the child expression
	 * @param expression the base unary expression
	 * @return GenericUnaryExpression where the variable is assigned the new value
	 */
	
	public GenericUnaryExpression replace(GenericUnaryExpression expression){
		return new GenericUnaryExpression(replace(expression.getExpression()), expression.getOperator());
	}
	
	/**
	 * Replaces a GenericParenthesizedElement by replacing the inner element
	 * @param element the base parenthesized element
	 * @return GenericParenthesizedElement where the variable is assigned the new value
	 */
	
	public GenericParenthesizedElement replace(GenericParenthesizedElement element){
		return new GenericParenthesizedElement(replace(element.getElement()));
	}
	
	/**
	 * Replaces a GenericAbstractVariable by checking the name of the element and variable to be replaced. 
	 * If they match, the value is set to the new value and the variable is returned.
	 * Else replace is called on the value of the variable
	 * @param variable the base variable
	 * @return the variable with the new value if the variable name matches the element name, else a variable where the replaced is called on its value
	 */
	
	public GenericAbstractVariable replace(GenericAbstractVariable variable){
		if(currentVariableName.equals(variable.getName())){
			variable.setValue(currentValue);
			return variable;
		}
		else{
			if(variable instanceof GenericVariable){
				return new GenericVariable(variable.getName(), replace(variable.getValue()));
			}
			else if(variable instanceof GenericArrayVariable){
				GenericArrayVariable arrayVariable = (GenericArrayVariable)variable;
				GenericArrayVariable newVariable = arrayVariable.clone();
				newVariable.setValue(replace(newVariable.getValue()));
				for(int i = 0; i < arrayVariable.getLength(); i++){
					newVariable.getValues()[i] = replace(arrayVariable.getValues()[i]);					
				}
				return newVariable;
			}
			else if(variable instanceof GenericListVariable){
				GenericListVariable listVariable = (GenericListVariable)variable;
				GenericListVariable newVariable = listVariable.clone();
				newVariable.setValue(replace(newVariable.getValue()));
				return newVariable;
			}
			else{
				System.err.println("Unsupported variable type in VariableAssigner: " + variable);
				return null;
			}
		}
	}
	
	/**
	 * Replaces a GenericAbstractVariableIndexReference by replacing its variable and index
	 * @param reference the base reference
	 * @return GenericAbstractVariableIndexReference where the variable is assigned the new value
	 */
	
	public GenericAbstractVariableIndexReference replace(GenericAbstractVariableIndexReference reference){
		return new GenericAbstractVariableIndexReference((GenericAbstractVariable) replace(reference.getVariable()), replace(reference.getIndex()));
	}
	
	/**
	 * Replaces a GenericAbstractVariableReference by replacing the referenced variable in the reference
	 * @param reference the base reference
	 * @return GenericAbstractVariableReference where the variable is assigned the new value
	 */
	
	public GenericAbstractVariableReference replace(GenericAbstractVariableReference reference){
		if(reference.getVariable() instanceof GenericArrayVariable){
			GenericArrayVariable replacedArrayVariable = (GenericArrayVariable) replace(reference.getVariable());
			return new GenericAbstractVariableReference(replacedArrayVariable);
		}
		else if(reference.getVariable() instanceof GenericVariable){
			GenericVariable replacedVariable = (GenericVariable) replace(reference.getVariable());
			return new GenericAbstractVariableReference(replacedVariable);
		}
		else if(reference.getVariable() instanceof GenericListVariable){
			GenericListVariable replacedListVariable = (GenericListVariable) replace(reference.getVariable());
			return new GenericAbstractVariableReference(replacedListVariable);
		}
		else{
			System.err.println("Encountered unsupported variable type while replacing GenericAbstractVariableReference: " + reference);
			return null;
		}
	}
	
	/**
	 * Replaces a GenericConditional by switching on the type
	 * @param conditional the base conditional
	 * @return GenericConditional where the variable is assigned the new value or null if a unsupported conditional is given
	 */

	public GenericConditional replace(GenericConditional conditional){
		if(conditional instanceof GenericIfConditional){
			return replace((GenericIfConditional) conditional);
		}
		else{
			System.err.println("Encountered unsupported Conditional: " + conditional);
			return null;
		}
	}
	
	/**
	 * Replaces a GenericIfConditional by replacing the condition, true clause and false clause
	 * @param ifCond the base GenericIfConditional
	 * @return GenericIfConditional where the variable is assigned the new value
	 */
	
	public GenericIfConditional replace(GenericIfConditional ifCond){
		if(ifCond.containsElse()){
			return new GenericIfConditional(ifCond.getName(), replace(ifCond.getCondition()), replace(ifCond.getIfClause()), replace(ifCond.getElseClause()));
		}
		else{
			return new GenericIfConditional(ifCond.getName(), replace(ifCond.getCondition()), replace(ifCond.getIfClause()));
		}
	}
	
	/**
	 * Replaces a GenericFunctionReference by replacing the parameters
	 * If the function has a valid output for the parameters, return the output
	 * Else return the reference with replaced parameters
	 * @param functionReference the base function reference
	 * @return The output if a valid input parameter is given, else a GenericFunctionReference with replaced parameters
	 */
	
	public GenericAbstractElement replace(GenericFunctionReference functionReference){
		
		//Replace the parameters
		List<GenericAbstractElement> replacedParameters = new ArrayList<GenericAbstractElement>();
		for(GenericAbstractElement element : functionReference.getParameters()){
			replacedParameters.add(replace(element));
		}
		
		//If the parameter is a variable or unary expression (supported types), evaluate these 
		List<GenericAbstractElement> atomicElements = new ArrayList<GenericAbstractElement>();
		for(GenericAbstractElement element : replacedParameters){
			if(element instanceof GenericAbstractVariable){
				atomicElements.add(new GenericOptions().evaluate(element));
			}
			else if(element instanceof GenericUnaryExpression){
				atomicElements.add(element);
			}
			else atomicElements.add(element);
		}

		//Calculate the value of the formula using the evaluated elements
		GenericAbstractElement value = functionReference.getFunction().getValue(atomicElements);

		//If the function returns a valid result, return the result
		if(value != null){
			return value;
		}
		
		//Else return the function reference with replaced parameters
		return new GenericFunctionReference(functionReference.getFunction(), replacedParameters);
	}


}
