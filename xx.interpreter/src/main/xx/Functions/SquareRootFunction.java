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
 * Function that returns the square root value of a number
 * @author Robin ten Buuren
 *
 */

public class SquareRootFunction extends GenericFunction {

	public SquareRootFunction() {
		super("SquareRoot");
	}
	
	/**
	 * Returns the square root value given a parameter
	 * @param parameters the list of parameters
	 * @return the square root value of the parameters
	 */

	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		//Only one parameter should be given
		if(parameters.size() == 1){
			GenericAbstractElement parameter = parameters.get(0);
			
			//If the parameter is a GenericNumber
			if(parameter instanceof GenericNumber){
				GenericNumber number = (GenericNumber)parameter;
				double numberValue = number.getValue();
				return new GenericNumber(Math.sqrt(numberValue));
			}
			
			//If the parameter is a GenericUnaryExpresssion
			else if(parameter instanceof GenericUnaryExpression){
				GenericUnaryExpression expression = (GenericUnaryExpression)parameter;
				GenericNumber number = (GenericNumber) expression.getExpression();
				switch(expression.getOperator()){
					case POSITIVE: return new GenericUnaryExpression(new GenericNumber(Math.sqrt(number.getValue())), GenericUnaryOperator.POSITIVE);
					case NEGATIVE: System.err.println("Unsupported unary operator on number in SquareRootFunction");
									break;
					case NEGATE: System.err.println("Unsupported unary operator on number in SquareRootFunction");
				}
			}
			
			//If the parameter is a GenericAbstractVariable
			else if(parameter instanceof GenericAbstractVariable){
				List<GenericAbstractElement> tempList = new ArrayList<GenericAbstractElement>();
				tempList.add(((GenericAbstractVariable) parameter).getValue());
				return getValue(tempList);
			}
			
			else{
				System.err.println("Unsupported parameter type found in SquareRootFunction");
			}
		}
		
		//Other cases result in a GenericNull for now
		System.err.println("Unsupported number of parameters found in SquareRootFunction");
		return new GenericNull();
	}

}
