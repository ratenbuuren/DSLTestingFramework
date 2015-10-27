package xx.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericAbstractVariableIndexReference;
import xx.GenericElements.GenericAbstractVariableReference;
import xx.GenericElements.GenericArrayVariable;
import xx.GenericElements.GenericBinaryExpression;
import xx.GenericElements.GenericBinaryOperator;
import xx.GenericElements.GenericBoolean;
import xx.GenericElements.GenericConditional;
import xx.GenericElements.GenericContainer;
import xx.GenericElements.GenericFunctionReference;
import xx.GenericElements.GenericIfConditional;
import xx.GenericElements.GenericListVariable;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericParenthesizedElement;
import xx.GenericElements.GenericString;
import xx.GenericElements.GenericUnaryExpression;
import xx.GenericElements.GenericUnaryOperator;
import xx.GenericElements.GenericVariable;

/**
 * Class used to generate a list of values for the variables in their context (e.g. if expression) 
 * Using the resulting values, all branch/condition coverage cases can be generated
 * @author Robin ten Buuren
 *
 */
public class GenericValueGenerator {
	
	//The resulting map of context and their variables and values
	private Map<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> possibleValues = new HashMap<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>>();
	
	//The current context of the variables
	private GenericAbstractElement currentContext;
	
	/**
	 * Removes the empty entries from the list of possible values
	 * These entries are the contexts (e.g. variables with a value) that do not use the unassigned variables
	 */
	
	public void removeEmptyEntries(){
		Iterator<Entry<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>>> it = possibleValues.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> e = it.next();
		    if (e.getValue().isEmpty()) {
		        it.remove();
		    }
		}
	}
	
	/**
	 * Returns the cleaned map of variable values
	 * @return the map of possible values (contexts, their variables and their values)
	 */
	
	public Map<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> getPossibleValues(){
		removeEmptyEntries();
		return possibleValues;
	}
	
	/**
	 * Generates the possible values given a context and elements
	 * @param context the context in which the element is used
	 * @param element the element for which the values should be generated
	 */
	
	public void generate(GenericAbstractElement context, GenericAbstractElement element){
		if(currentContext == null || !currentContext.equals(context)){
			currentContext = context;
			possibleValues.put(currentContext, new HashMap<GenericAbstractElement, List<GenericAbstractElement>>());
		}
		check(element);
	}
	
	/**
	 * Checks whether the current element contains a binary expression with unassigned variables
	 * @param element the element to be checked
	 */
	
	public void check(GenericAbstractElement element) {
		if (element instanceof GenericBinaryExpression) {
			check((GenericBinaryExpression)element);
		}
		else if (element instanceof GenericUnaryExpression){
			check((GenericUnaryExpression)element);
		}
		else if(element instanceof GenericParenthesizedElement){
			check(((GenericParenthesizedElement) element).getElement());
		}
		else if(element instanceof GenericVariable){
			check((GenericVariable) element);
		}
		else if(element instanceof GenericContainer){
			check((GenericContainer) element);
		}
		else if(element instanceof GenericFunctionReference) {
			check((GenericFunctionReference) element);
		}
		else if(element instanceof GenericConditional){
			check((GenericConditional) element);
		}
		else if(element instanceof GenericArrayVariable){
			check((GenericArrayVariable) element);
		}
		else if(element instanceof GenericAbstractVariableReference){
			check((GenericAbstractVariableReference) element);
		}
		else if(element instanceof GenericAbstractVariableIndexReference){
			check((GenericAbstractVariableIndexReference) element);
		}
		else if(element instanceof GenericListVariable){
			check((GenericListVariable) element);
		}
		else if(element instanceof GenericNumber || element instanceof GenericString || element instanceof GenericBoolean || element instanceof GenericNull){
			return;
		}
		else{
			System.err.println("Encountered unknown Value Generator Element: " + element);
		}
	}
	
	/**
	 * Checks whether the container contains a binary expression with unassigned variables by checking its elements
	 * @param container the GenericContainer to be checked
	 */
	
	public void check(GenericContainer container){
		for(GenericAbstractElement element : container.getElements()){
			check(element);
		}
	}
	
	/**
	 * Checks whether the function contains a binary expression with unassigned variables by checking its parameters
	 * @param function the GenericFunction to be checked
	 */
	
	public void check(GenericFunctionReference functionReference){
		for(GenericAbstractElement element : functionReference.getParameters()){
			check(element);
		}
	}

	/**
	 * Checks whether the variable contains a binary expression with unassigned variables by checking its value
	 * @param variable the GenericVariable to be checked
	 */
	
	public void check(GenericVariable variable){
		check(variable.getValue());
	}
	
	/**
	 * Checks whether the conditional contains a binary expression with unassigned variables by checking its type and forwarding the method
	 * @param conditional the GenericConditional to be checked
	 */
	
	public void check(GenericConditional conditional){
		if(conditional instanceof GenericIfConditional){
			check((GenericIfConditional) conditional);
		}
	}
	
	/**
	 * Checks whether the if conditional contains a binary expression with unassigned variables by checking its condition, true clause and false clause
	 * @param ifCond the GenericIfConditional to be checked
	 */
	
	public void check(GenericIfConditional ifCond){
		check(ifCond.getCondition());
		check(ifCond.getIfClause());
		check(ifCond.getElseClause());
	}
	
	/**
	 * Checks whether the unary expression contains a binary expression with unassigned variables by checking its child expression
	 * @param expression the GenericUnaryExpression to be checked
	 */
	
	public void check(GenericUnaryExpression expression){
		check(expression.getExpression());
	}
	
	/**
	 * Checks whether the array variable contains a binary expression with unassigned variables by checking its elements
	 * @param arrayVariable the GenericArrayVariable to be checked
	 */
	
	public void check(GenericArrayVariable arrayVariable){
		for(int i = 0; i < arrayVariable.getValues().length; i++){
			check(arrayVariable.getValue(i));
		}
	}
	
	/**
	 * Checks whether the abstract variable index reference contains a binary expression with unassigned variables by checking the referenced variable
	 * @param variableIndexReference the GenericAbstractVariableIndexReference to be checked
	 */
	
	public void check(GenericAbstractVariableIndexReference variableIndexReference){
		check(variableIndexReference.getVariable());
	}
	
	/**
	 * Checks whether the abstract variable reference index contains a binary expression with unassigned variables by checking the referenced variable
	 * @param VariableReference the GenericAbstractVariableReference to be checked
	 */
	
	public void check(GenericAbstractVariableReference VariableReference){
		check(VariableReference.getVariable());
	}
	
	/**
	 * Checks whether the list variable contains a binary expression with unassigned variables by checking its elements
	 * @param listVariable the GenericListVariable to be checked
	 */
	
	public void check(GenericListVariable listVariable){
		for(int i = 0; i < listVariable.getValues().size(); i++){
			check(listVariable.getValue(i));
		}
	}
	
	/**
	 * Checks whether the binary expression contains a unassigned variable (GenericAbstractVariable with value GenericNull) by checking the operator
	 * If the operator equals >, >=, <, <=, == or !=, then values should be generated if there are unassigned variables are present, else the child expressions are checked
	 * @param expression the GenericBinaryExpression to be checked
	 */
	
	public void check(GenericBinaryExpression expression){
		GenericBinaryOperator op = expression.getOperator();
		//These six operators require two values for condition coverage
		if(op == GenericBinaryOperator.GT || op == GenericBinaryOperator.GTE || op == GenericBinaryOperator.LT
				|| op == GenericBinaryOperator.LTE || op == GenericBinaryOperator.EQUALS || op == GenericBinaryOperator.NOTEQUALS){
			//Is the left child a variable?
			if(expression.getLeft() instanceof GenericAbstractVariable){
				GenericAbstractVariable variable = (GenericAbstractVariable) expression.getLeft();
				//Is the variable unassigned (value = GenericNull)?
				if(variable.getValue() instanceof GenericNull){
					possibleValues.get(currentContext).put(variable, possibleValues(expression.getRight(), expression.getOperator(), true));
				}
			}
			//Is the right child a variable?
			else if(expression.getRight() instanceof GenericAbstractVariable){
				GenericAbstractVariable variable = (GenericAbstractVariable) expression.getRight();
				//Is the variable unassigned (value = GenericNull)?
				if(variable.getValue() instanceof GenericNull){
					possibleValues.get(currentContext).put(variable, possibleValues(expression.getLeft(), expression.getOperator(), false));
				}
			}
			//Else check the children of the binary expression
			else{
				check(expression.getLeft());
				check(expression.getRight());
			}
		}
		//Else check the children of the binary expression
		else{
			check(expression.getLeft());
			check(expression.getRight());
		}
	}
	
	/**
	 * Generates possible values based on the type of the element
	 * @param element the element for which values have to be generated
	 * @param operator the binary operator of the element
	 * @param leftVariable whether the left side is the variable (true), or the right side (false) 
	 * @return two values to condition coverage the binary expression
	 */
	
	public List<GenericAbstractElement> possibleValues(GenericAbstractElement element, GenericBinaryOperator operator, boolean leftVariable){
		if(element instanceof GenericBoolean){
			return possibleBooleans(((GenericBoolean)element).getValue(), operator);
		}
		else if(element instanceof GenericNumber){
			return possibleNumbers(((GenericNumber)element).getValue(), operator, leftVariable);
		}
		else if(element instanceof GenericString){
			return possibleStrings(((GenericString)element).getValue(), operator);
		}
		else if(element instanceof GenericUnaryExpression){
			return possibleUnaryExpressions(((GenericUnaryExpression)element), operator, leftVariable);
		}
		else{
			System.err.println("Unsupported possibleValues element encountered: " + element);
			GenericOptions options = new GenericOptions();
			return possibleValues(options.evaluate(element), operator, leftVariable);
		}
	}
	
	/**
	 * Generates the GenericNumber values for a value and operator 
	 * @param value the value for which the other case has to be generated
	 * @param operator the operator of the binary expression
	 * @param leftVariable whether the left side is the variable (true), or the right side (false) 
	 * @return two GenericNumber values to condition coverage the binary expression
	 */
	
	public List<GenericAbstractElement> possibleNumbers(double value, GenericBinaryOperator operator, boolean leftVariable){
		List<GenericAbstractElement> result = new ArrayList<GenericAbstractElement>();
		GenericNumber number1 = new GenericNumber(value);
		GenericNumber number2 = new GenericNumber(value);
		switch(operator){
		//Increment the first number
		case GT:
			if(leftVariable){
				number1.setValue(value + 1);
			}
			else {
				number1.setValue(value - 1);
			}
			break;
		//Decrement the first number
		case LT:
			if(leftVariable){
				number1.setValue(value - 1);
			}
			else {
				number1.setValue(value + 1);
			}
			break;
		//Decrement the second number
		case GTE:
			if(leftVariable){
				number2.setValue(value - 1);
			}
			else {
				number2.setValue(value + 1);
			}
			break;
		//Increment the first number
		case LTE:
			if(leftVariable){
				number2.setValue(value + 1);
			}
			else {
				number2.setValue(value - 1);
			}
			break;
		//Increment the second number (or decrement, does not matter)
		case EQUALS:
			number2.setValue(value + 1);
			break;
		//Increment the first number (or decrement, does not matter)
		case NOTEQUALS:
			number1.setValue(value + 1);
			break;
		default:
			break; 
		}
		result.add(number1);
		result.add(number2);
		return result;
	}
	
	/**
	 * Generates the GenericBoolean values for a value and operator 
	 * @param value the value for which the other case has to be generated
	 * @param operator the operator of the binary expression
	 * @return two GenericBoolean values to condition coverage the binary expression
	 */
	
	public List<GenericAbstractElement> possibleBooleans(boolean value, GenericBinaryOperator operator){
		List<GenericAbstractElement> result = new ArrayList<GenericAbstractElement>();
		GenericBoolean boolean1 = new GenericBoolean(value);
		GenericBoolean boolean2 = new GenericBoolean(value);
		switch(operator){
		case EQUALS:
			boolean2.setValue(!value);
			break;
		case NOTEQUALS:
			boolean1.setValue(!value);
			break;
		default:
			break; 
		}
		result.add(boolean1);
		result.add(boolean2);
		return result;
	}
	
	/**
	 * Generates the GenericString values for a value and operator 
	 * @param value the value for which the other case has to be generated
	 * @param operator the operator of the binary expression
	 * @return two GenericString values to condition coverage the binary expression
	 */
	
	public List<GenericAbstractElement> possibleStrings(String value, GenericBinaryOperator operator){
		List<GenericAbstractElement> result = new ArrayList<GenericAbstractElement>();
		GenericString string1 = new GenericString(value);
		GenericString string2 = new GenericString(value);
		switch(operator){
		case EQUALS:
			string2.setValue("Not " + value);
			break;
		case NOTEQUALS:
			string1.setValue("Not " + value);
			break;
		default:
			break; 
		}
		result.add(string1);
		result.add(string2);
		return result;
	}
	
	/**
	 * Generates the GenericUnaryExpression values for a value and operator 
	 * @param value the value for which the other case has to be generated
	 * @param operator the operator of the binary expression
	 * @param leftVariable whether the left side is the variable (true), or the right side (false) 
	 * @return two GenericUnaryExpression values to condition coverage the binary expression
	 */
	
	public List<GenericAbstractElement> possibleUnaryExpressions(GenericUnaryExpression expression, GenericBinaryOperator operator, boolean leftVariable){
		List<GenericAbstractElement> result = new ArrayList<GenericAbstractElement>();
		GenericUnaryOperator unaryOperator = expression.getOperator();
		switch(unaryOperator){
		case NEGATE:
			if(expression.getExpression() instanceof GenericBoolean){
				List<GenericAbstractElement> booleans = possibleBooleans(((GenericBoolean)expression.getExpression()).getValue(), operator);
				for(GenericAbstractElement element : booleans){
					result.add(new GenericUnaryExpression(element, unaryOperator));
				}
			}
			return result;
		case POSITIVE:
			if(expression.getExpression() instanceof GenericNumber){
				List<GenericAbstractElement> numbers = possibleNumbers(((GenericNumber)expression.getExpression()).getValue(), operator, leftVariable);
				for(GenericAbstractElement element : numbers){
					result.add(new GenericUnaryExpression(element, unaryOperator));
				}
			}
			return result;
		case NEGATIVE:	
			if(expression.getExpression() instanceof GenericNumber){
				List<GenericAbstractElement> numbers = possibleNumbers(((GenericNumber)expression.getExpression()).getValue(), operator, !leftVariable);
				for(GenericAbstractElement element : numbers){
					result.add(new GenericUnaryExpression(element, unaryOperator));
				}
			}
			return result;
		}
		return result;
	}
}
