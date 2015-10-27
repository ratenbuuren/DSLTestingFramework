package xx.Functions;

import java.util.ArrayList;
import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericFunction;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericUnaryExpression;
import xx.GenericElements.GenericUnaryOperator;

/**
 * Function that returns the floor value of a number
 * @author Robin ten Buuren
 *
 */

public class FloorFunction extends GenericFunction {

	public FloorFunction() {
		super("Floor");
	}

	/**
	 * Returns the floor value given a parameter
	 * @param parameters the list of parameters
	 * @return the floor value of the parameters
	 */
	
	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		//Only one parameter should be given
		if(parameters.size() == 1){
			GenericAbstractElement parameter = parameters.get(0);
			
			//If the parameter is a GenericNumber
			if(parameter instanceof GenericNumber){
				GenericNumber number = (GenericNumber)parameter;
				return new GenericNumber(Math.floor(number.getValue()));
			}
			
			//If the parameter is a GenericUnaryExpresssion
			else if(parameter instanceof GenericUnaryExpression){
				GenericUnaryExpression expression = (GenericUnaryExpression)parameter;
				GenericNumber number = (GenericNumber) expression.getExpression();
				switch(expression.getOperator()){
					case POSITIVE: return new GenericUnaryExpression(new GenericNumber(Math.floor(number.getValue())), GenericUnaryOperator.POSITIVE);
					case NEGATIVE: return new GenericUnaryExpression(new GenericNumber(Math.floor(number.getValue() * -1) * -1), GenericUnaryOperator.NEGATIVE);
					case NEGATE: System.err.println("Unsupported unary operator on number in FloorFunction");
				}
			}
			
			//If the parameter is a GenericAbstractVariable
			else if(parameter instanceof GenericAbstractVariable){
				List<GenericAbstractElement> tempList = new ArrayList<GenericAbstractElement>();
				tempList.add(((GenericAbstractVariable) parameter).getValue());
				return getValue(tempList);
			}
			
			else{
				System.err.println("Unsupported parameter type found in FloorFunction");
			}
		}
		
		//Other cases result in a GenericNull for now
		System.err.println("Unsupported number of parameters found in FloorFunction");
		return new GenericNull();
	}

}
