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
 * Function that returns the rounded value of numbers
 * @author Robin ten Buuren
 *
 */

public class RoundFunction extends GenericFunction {

	public RoundFunction() {
		super("Round");
	}

	/**
	 * Returns the rounded value given a list of parameters
	 * @param parameters the list of parameters
	 * @return the rounded value of the parameters
	 */
	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		//Only one parameter should be given
		if(parameters.size() == 1){
			GenericAbstractElement parameter = parameters.get(0);
			
			//If the parameter is a GenericNumber
			if(parameter instanceof GenericNumber){
				GenericNumber number = (GenericNumber)parameters.get(0);
				return new GenericNumber(Math.ceil(number.getValue()));
			}
			
			//If the parameter is a GenericUnaryExpresssion
			else if(parameter instanceof GenericUnaryExpression){
				GenericUnaryExpression expression = (GenericUnaryExpression)parameter;
				GenericNumber number = (GenericNumber) expression.getExpression();
				switch(expression.getOperator()){
					case POSITIVE : return new GenericUnaryExpression(new GenericNumber(Math.ceil(number.getValue())), GenericUnaryOperator.POSITIVE);
					case NEGATIVE: return new GenericUnaryExpression(new GenericNumber(Math.ceil(number.getValue() * -1) * -1), GenericUnaryOperator.NEGATIVE);
					case NEGATE: System.err.println("Unsupported unary operator on number in RoundFunction");
				}
			}
			
			//If the parameter is a GenericAbstractVariable
			else if(parameter instanceof GenericAbstractVariable){
				List<GenericAbstractElement> tempList = new ArrayList<GenericAbstractElement>();
				tempList.add(((GenericAbstractVariable) parameter).getValue());
				return getValue(tempList);
			}
			
			else{
				System.err.println("Unsupported parameter type found in RoundFunction");
			}
		}
		else{
			System.err.println("Unsupported number of parameters found in RoundFunction");
		}
		return new GenericNull();
	}

}
