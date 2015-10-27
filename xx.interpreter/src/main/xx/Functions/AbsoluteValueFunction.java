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
 * Function that returns the absolute value of a number
 * @author Robin ten Buuren
 *
 */

public class AbsoluteValueFunction extends GenericFunction {

	public AbsoluteValueFunction() {
		super("AbsoluteValue");
	}
	
	/**
	 * Returns the absolute value given a parameter
	 * @param parameters the list of parameters
	 * @return the absolute value of the parameters
	 */
	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		//Only one parameter should be given
		if(parameters.size() == 1){
			GenericAbstractElement parameter = parameters.get(0);
			
			//If the parameter is a GenericNumber
			if(parameter instanceof GenericNumber){
				GenericNumber number = (GenericNumber)parameter;
				return new GenericNumber(Math.abs(number.getValue()));
			}
			
			//If the parameter is a GenericUnaryExpresssion
			else if(parameter instanceof GenericUnaryExpression){
				GenericUnaryExpression expression = (GenericUnaryExpression)parameter;
				//Expression must be a number and operator must be negative or positive
				if(expression.getExpression() instanceof GenericNumber && 
						(expression.getOperator() == GenericUnaryOperator.NEGATIVE || expression.getOperator() == GenericUnaryOperator.POSITIVE)){
					GenericNumber number = (GenericNumber) expression.getExpression();
					return new GenericNumber(Math.abs(number.getValue()));
				}
			}
			
			//If the parameter is a GenericAbstractVariable
			else if(parameter instanceof GenericAbstractVariable){
				List<GenericAbstractElement> tempList = new ArrayList<GenericAbstractElement>();
				tempList.add(((GenericAbstractVariable) parameter).getValue());
				return getValue(tempList);
			}
			
			else{
				System.err.println("Unsupported parameter type found in AbsoluteValueFunction");
			}
		}
		
		//Other cases result in a GenericNull for now
		System.err.println("Unsupported number of parameters found in AbsoluteValueFunction");
		return new GenericNull();
	}

}
