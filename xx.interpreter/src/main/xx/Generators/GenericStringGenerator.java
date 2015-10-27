package xx.Generators;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericAbstractVariableReference;
import xx.GenericElements.GenericBinaryExpression;
import xx.GenericElements.GenericBoolean;
import xx.GenericElements.GenericConditional;
import xx.GenericElements.GenericContainer;
import xx.GenericElements.GenericIfConditional;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericParenthesizedElement;
import xx.GenericElements.GenericString;
import xx.GenericElements.GenericUnaryExpression;

/**
 * Class used to transform a GenericAbstractElement to a String representation
 * @author Robin ten Buuren
 */

public class GenericStringGenerator {

	/**
	 * Transforms a GenericAbstractElement to a String representation by recursively transforming the expressions inside the GenericAbstractElement
	 * @param element the GenericAbstractElement to be transformed
	 * @return the String representation of the GenericAbstractElement
	 */
	public String transform(GenericAbstractElement element) {
		if (element instanceof GenericBinaryExpression) {
			return transform((GenericBinaryExpression)element);
		}
		else if (element instanceof GenericUnaryExpression){
			return transform((GenericUnaryExpression)element);
		}
		else if(element instanceof GenericParenthesizedElement){
			return "(" + transform(((GenericParenthesizedElement) element).getElement()) + ")";
		}
		else if(element instanceof GenericAbstractVariable){
			return transform((GenericAbstractVariable) element);
		}
		else if(element instanceof GenericAbstractVariableReference){
			return transform((GenericAbstractVariableReference) element);
		}
		else if (element instanceof GenericNumber) {
			return "" + ((GenericNumber) element).getValue();
		}
		else if(element instanceof GenericBoolean) {
			return "" + ((GenericBoolean) element).getValue();
		}
		else if(element instanceof GenericString) {
			return "'" + ((GenericString) element).getValue() + "'";
		}
		else if(element instanceof GenericContainer){
			return transform((GenericContainer) element);
		}
		else if(element instanceof GenericConditional){
			return transform((GenericConditional) element);
		}
		else if(element instanceof GenericNull){
			return "null";
		}
		else{
			return "String generator encountered unsupported type: " + element;
		}
	}
	
	/**
	 * Transforms a GenericAbstractVariable by converting its value
	 * @param variable the variable to be transformed
	 * @return the transformed variable
	 */
	
	public String transform(GenericAbstractVariable variable){
		return transform(variable.getValue());
	}
	
	/**
	 * Transforms a GenericAbstractVariableReference by converting its variable
	 * @param reference the reference to be transformed
	 * @return the transformed reference
	 */
	
	public String transform(GenericAbstractVariableReference reference){
		return transform(reference.getVariable());
	}
	
	/**
	 * Transforms a GenericBinaryExpression by transforming itself and its child values
	 * @param bE the binary expression to be transformed
	 * @return the transformed binary expression
	 */
	
	public String transform(GenericBinaryExpression bE) {
		return "(" + transform(bE.getLeft()) + bE.getOperator().getSymbol() + transform(bE.getRight()) + ")";
	}
	
	/**
	 * Transforms a GenericUnaryExpression by transforming itself and its child value
	 * @param uE the unary expression to be transformed
	 * @return the transformed unary expression
	 */
	
	public String transform(GenericUnaryExpression uE) {
		return "(" + uE.getOperator().getSymbol() + transform(uE.getExpression()) + ")";
	}
	
	/**
	 * Transforms a GenericConditional by checking its type
	 * @param conditional the conditional to be transformed
	 * @return the transformed conditional
	 */
	
	public String transform(GenericConditional conditional){
		if(conditional instanceof GenericIfConditional){
			return transform((GenericIfConditional) conditional);
		}
		else return "String generator encountered unsupported Conditional: " + conditional;
	}
	
	/**
	 * Transforms a GenericIfConditional by transforming itself, the condition, the true clause and the false clause (if present)
	 * @param ifCond the If conditional to be transformed
	 * @return the transformed GenericIfConditional
	 */
	
	public String transform(GenericIfConditional ifCond){
		if(ifCond.containsElse()){
			return "if" + transform(ifCond.getCondition()) + "{" + transform(ifCond.getIfClause()) + "}else{" + transform(ifCond.getElseClause()) + "}";
		}
		else{
			return "if" + transform(ifCond.getCondition()) + "{" + transform(ifCond.getIfClause()) + "}";
		}
	}
	
	/**
	 * Transforms a GenericContainer by transforming itself and its elements
	 * @param container the container to be transformed
	 * @return the transformed container
	 */
	
	public String transform(GenericContainer container){
		String result = "[";
		for(GenericAbstractElement element : container.getElements()){
			result += transform(element) + ",";
		}
		result = RemoveLastComma(result);
		return result + "]";
	}
	
	/**
	 * Removes the last comma of a String the if the String is not null, not empty and its last character is a comma
	 * @param string the String of which the last comma has to be removed
	 * @return the String where the comma is removed
	 */
	public String RemoveLastComma(String string) {
	    if (null != string && string.length() > 0 && string.charAt(string.length()-1)==',') {
	      return string.substring(0, string.length()-1);
	    }
	    return string;
	}
}
