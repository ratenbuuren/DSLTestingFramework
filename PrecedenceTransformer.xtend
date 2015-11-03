package org.xtext.evaluation;

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.xtext.operator.precedence.AbstractElement
import org.xtext.operator.precedence.BinaryExpression
import org.xtext.operator.precedence.BinaryOperator
import org.xtext.operator.precedence.BoolConstant
import org.xtext.operator.precedence.Container
import org.xtext.operator.precedence.ContainerRef
import org.xtext.operator.precedence.DoubleConstant
import org.xtext.operator.precedence.Function
import org.xtext.operator.precedence.FunctionType
import org.xtext.operator.precedence.ListVariable
import org.xtext.operator.precedence.ListVariableRef
import org.xtext.operator.precedence.MockDef
import org.xtext.operator.precedence.MockEntry
import org.xtext.operator.precedence.MockRef
import org.xtext.operator.precedence.ParenthesizedExpression
import org.xtext.operator.precedence.StringConstant
import org.xtext.operator.precedence.UnaryExpression
import org.xtext.operator.precedence.UnaryOperator
import org.xtext.operator.precedence.Variable
import org.xtext.operator.precedence.VariableRef
import xx.Functions.AbsoluteValueFunction
import xx.Functions.RoundFunction
import xx.Functions.SumFunction
import xx.GenericElements.GenericAbstractElement
import xx.GenericElements.GenericAbstractVariable
import xx.GenericElements.GenericBinaryExpression
import xx.GenericElements.GenericBinaryOperator
import xx.GenericElements.GenericBoolean
import xx.GenericElements.GenericContainer
import xx.GenericElements.GenericFunction
import xx.GenericElements.GenericFunctionReference
import xx.GenericElements.GenericIfConditional
import xx.GenericElements.GenericListVariable
import xx.GenericElements.GenericNumber
import xx.GenericElements.GenericParenthesizedElement
import xx.GenericElements.GenericString
import xx.GenericElements.GenericUnaryExpression
import xx.GenericElements.GenericUnaryOperator
import xx.GenericElements.GenericVariable

/**
 * Class that transforms Precedence elements to generic framework elements
 */
 
public class PrecedenceTransformer {
	
	Map<String, GenericAbstractVariable> variables = new HashMap<String, GenericAbstractVariable>();
	Map<String, GenericContainer> containers = new HashMap<String, GenericContainer>();
	Map<String, GenericFunction> functions = new HashMap<String, GenericFunction>();
	
	/** 
	 * Transforms the different types of elements by using polymorphism
	 * @param element the element to be transformed
	 * @return the transformed element
	 */
	
	def GenericAbstractElement transform(AbstractElement element){
		if(element instanceof VariableRef){
			transform(element as VariableRef)
		}
		else if(element instanceof BinaryExpression){
			transform(element as BinaryExpression)
		}
		else if(element instanceof UnaryExpression){
			transform(element as UnaryExpression)
		}
		else if(element instanceof ParenthesizedExpression){
			transform(element as ParenthesizedExpression)
		}
		else if(element instanceof DoubleConstant){
			transform(element as DoubleConstant)
		}
		else if(element instanceof BoolConstant){
			transform(element as BoolConstant)
		}
		else if(element instanceof StringConstant){
			transform(element as StringConstant)
		}
		else if(element instanceof Container){
			transform(element as Container)
		}
		else if(element instanceof ContainerRef){
			transform(element as ContainerRef)
		}
		else if(element instanceof Function){
			transform(element as Function);
		}
		else if(element instanceof Variable){
			transform(element as Variable);
		}
		else if(element instanceof MockDef){
			transform(element as MockDef);
		}
		else if(element instanceof MockEntry){
			transform(element as MockEntry);
		}
		else if(element instanceof MockRef){
			transform(element as MockRef);	
		}
		else if(element instanceof ListVariable){
			transform(element as ListVariable);
		}
		else if(element instanceof ListVariableRef){
			transform(element as ListVariableRef);
		}
		else {
			throw new Exception("Transformer encountered unsupported AbstractElement type: " + element);
		}
	}
	
	/**
	 * Transforms MockDef elements
	 * @param mockDef the MockDef to be transformed
	 * @return the transformed MockDef
	 */
	
	def GenericFunction transform(MockDef mockDef){
		if(!functions.containsKey(mockDef.name)){
			functions.put(mockDef.name, new GenericFunction(mockDef.name));
		}
		return functions.get(mockDef.name);
	}
	
	/**
	 * Transforms MockEntry elements
	 * @param entry the MockEntry to be transformed
	 * @return the transformed MockEntry
	 */
	
	def GenericFunction transform(MockEntry entry){
		var List<GenericAbstractElement> parameters = new ArrayList<GenericAbstractElement>();
		for(AbstractElement element : entry.parameters){
			parameters.add(transform(element));
		}
		functions.get(entry.type.name).insertEntry(parameters, transform(entry.result));
		return functions.get(entry.type.name);
	}
	
	/**
	 * Transforms MockRef elements
	 * @param reference the MockRef to be transformed
	 * @return the transformed MockRef
	 */
	
	def GenericFunctionReference transform(MockRef reference){
		var List<GenericAbstractElement> parameters = new ArrayList<GenericAbstractElement>();
		for(AbstractElement element : reference.parameters){
			parameters.add(transform(element));
		}
		return new GenericFunctionReference(functions.get(reference.mockFunction.name), parameters);
	}
	
	/**
	 * Transforms Variable elements
	 * @param variable the Variable to be transformed
	 * @return the transformed Variable
	 */

	def GenericAbstractVariable transform(Variable variable){
		//New variable
		if(variable.expression == null){
			variables.put(variable.name, new GenericVariable(variable.name));
			return variables.get(variable.name);
		}
		//Existing variable
		else{
			variables.put(variable.name, new GenericVariable(variable.name, transform(variable.expression)));
			return variables.get(variable.name);
		}
	}
	
	/**
	 * Transforms VariableRef elements
	 * @param reference the VariableRef to be transformed
	 * @return the referenced Variable
	 */
	
	def GenericAbstractVariable transform(VariableRef reference){
		return variables.get(reference.variable.name);
	}
	
	/**
	 * Transforms ListVariable elements
	 * @param listVariable the ListVariable to be transformed
	 * @return the transformed ListVariable
	 */
	
	def GenericAbstractVariable transform(ListVariable listVariable){
		//New variable
		if(listVariable.expression == null){
			variables.put(listVariable.name, new GenericListVariable(listVariable.name));
			return variables.get(listVariable.name);
		}
		//Existing variable
		else{
			variables.put(listVariable.name, new GenericListVariable(listVariable.name, transform(listVariable.expression), 10));
			return variables.get(listVariable.name);
		}
	}
	
	/**
	 * Transforms ListVariableRef elements
	 * @param reference the ListVariableRef to be transformed
	 * @return the referenced ListVariable
	 */
	
	def GenericAbstractVariable transform(ListVariableRef reference){
		return variables.get(reference.listvariable.name);
	}
	
	/**
	 * Transforms Container elements and their contents
	 * @param container the Container to be transformed
	 * @return the transformed Container
	 */
	
	def GenericContainer transform(Container container){
		var genericContainer = new GenericContainer();
		for(element: container.elements){
			genericContainer.addElement(transform(element));
		}
		containers.put(container.name, genericContainer);
		return genericContainer;
	}
	
	/**
	 * Transforms ContainerRef elements
	 * @param reference the ContainerRef to be transformed
	 * @return the referenced Container
	 */
	
	def GenericAbstractElement transform(ContainerRef reference){
		//Reference has a index
		if(reference.cell != null){
			var cell = transform(reference.cell) as GenericNumber;
			var container = containers.get(reference.container.name);
			return container.get(cell.value.intValue);
		}
		//No index present
		else return containers.get(reference.container.name);
	}
	
	/**
	 * Transforms Function elements and their parameters
	 * @param function the Function to be transformed
	 * @return the transformed Function
	 */

	def GenericAbstractElement transform(Function function){
		var parameters =  new ArrayList<GenericAbstractElement>();
		for(parameter : function.parameters){
			parameters.add(transform(parameter));
		}
		if(function.functionType == FunctionType.^IF){
			return transformIf(function);
		}
		else if(functions.get(function.name) != null){
			return new GenericFunctionReference(functions.get(function.name), parameters);
		}
		else{
			throw new Exception("Unsupported Function: " + function)
		}
	}
	
	/**
	 * Transforms IF Function elements and their parameters
	 * @param function the IF Function to be transformed
	 * @return the transformed IF Function
	 */
	
	def GenericIfConditional transformIf(Function function){
		var parameters =  new ArrayList<GenericAbstractElement>();
		for(parameter : function.parameters){
			parameters.add(transform(parameter));
		}
		switch(parameters.size){
			case 2 : return new GenericIfConditional(function.name, parameters.get(0), parameters.get(1))
			case 3 : return new GenericIfConditional(function.name, parameters.get(0), parameters.get(1), parameters.get(2))
			default: throw new Exception("Unsupported number of parameters in IfConditional " + function)
		}
	}
	
	/**
	 * Transforms BinaryExpression elements
	 * @param expression the BinaryExpression to be transformed
	 * @return the transformed BinaryExpression
	 */
		
	def GenericBinaryExpression transform(BinaryExpression expression){
		if(expression.op.equals(BinaryOperator.GREATER)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.GT)
		}
		if(expression.op.equals(BinaryOperator.SMALLER)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.LT)
		}
		if(expression.op.equals(BinaryOperator.GREATEREQUAL)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.GTE)
		}
		if(expression.op.equals(BinaryOperator.SMALLEREQUAL)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.LTE)
		}
		if(expression.op.equals(BinaryOperator.EQUAL)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.EQUALS)
		}
		if(expression.op.equals(BinaryOperator.NOTEQUAL)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.NOTEQUALS)
		}
		if(expression.op.equals(BinaryOperator.OR)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.OR)
		}
		if(expression.op.equals(BinaryOperator.AND)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.AND)
		}
		if(expression.op.equals(BinaryOperator.MULTIPLY)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.MULTIPLICATION)
		}
		if(expression.op.equals(BinaryOperator.DIVIDE)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.DIVISION)
		}
		if(expression.op.equals(BinaryOperator.PLUS)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.ADDITION)
		}
		if(expression.op.equals(BinaryOperator.MINUS)){
			return new GenericBinaryExpression(transform(expression.left), transform(expression.right), GenericBinaryOperator.SUBTRACTION)
		}
		else {
			throw new Exception("Unsupported BinaryOperator Type: " + expression.op)
		}
	}
	
	/**
	 * Transforms UnaryExpression elements
	 * @param expression the UnaryExpression to be transformed
	 * @return the transformed UnaryExpression
	 */
		
	def GenericUnaryExpression transform(UnaryExpression expression){
	if(expression.op.equals(UnaryOperator.NEGATE)){
		return new GenericUnaryExpression(transform(expression.expression), GenericUnaryOperator.NEGATE)
		}
	if(expression.op.equals(UnaryOperator.POSITIVE)){
		return new GenericUnaryExpression(transform(expression.expression), GenericUnaryOperator.POSITIVE)
		}
	if(expression.op.equals(UnaryOperator.NEGATIVE)){
		return new GenericUnaryExpression(transform(expression.expression), GenericUnaryOperator.NEGATIVE)
		}
	}
	
	/**
	 * Transforms ParenthesizedExpression elements
	 * @param expression the ParenthesizedExpression to be transformed
	 * @return the transformed ParenthesizedExpression
	 */
		
	def GenericParenthesizedElement transform(ParenthesizedExpression expression){
		return new GenericParenthesizedElement(transform(expression.innerExpression));
	}
	
	/**
	 * Transforms DoubleConstant elements
	 * @param expression the DoubleConstant to be transformed
	 * @return the transformed DoubleConstant
	 */
				
	def GenericNumber transform(DoubleConstant expression){
		return new GenericNumber(expression.value);
	}
	
	/**
	 * Transforms BoolConstant elements
	 * @param expression the BoolConstant to be transformed
	 * @return the transformed BoolConstant
	 */
	
	def GenericBoolean transform(BoolConstant expression){
		return new GenericBoolean(expression.value);
	}
	
	/**
	 * Transforms StringConstant elements
	 * @param expression the StringConstant to be transformed
	 * @return the transformed StringConstant
	 */
				
	def GenericString transform(StringConstant expression){
		return new GenericString(expression.value);
	}
	
	/**
	 * Method to define predefined and mock functions
	 */
	
	def void instantiateDefaultFunctions(){
		functions.put("Abs", new AbsoluteValueFunction());
		functions.put("Round", new RoundFunction());
		functions.put("Sum", new SumFunction());
	}
				
}