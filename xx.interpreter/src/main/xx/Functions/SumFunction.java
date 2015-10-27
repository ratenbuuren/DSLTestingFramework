package xx.Functions;

import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericFunction;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericUnaryExpression;

/**
 * Function that returns the sum value of the numbers
 * @author Robin ten Buuren
 *
 */

public class SumFunction extends GenericFunction {

	public SumFunction() {
		super("Sum");
	}

	/**
	 * Returns the sum value given a list of parameters
	 * @param parameters the list of parameters
	 * @return the sum value of the parameters
	 */
	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		double result = 0;
		//Traverse the parameters
		for(GenericAbstractElement parameter : parameters){
			
			//If the parameter is a GenericNumber
			if(parameter instanceof GenericNumber){
				result = result + ((GenericNumber) parameter).getValue();
			}
			
			//If the parameter is a GenericUnaryExpresssion
			else if(parameter instanceof GenericUnaryExpression){
				GenericUnaryExpression expression = (GenericUnaryExpression)parameter;
				GenericNumber number = (GenericNumber) expression.getExpression();
				switch(expression.getOperator()){
					case POSITIVE: result += number.getValue(); 
									break;
					case NEGATIVE: result -= number.getValue();
									break;
					case NEGATE: System.err.println("Unsupported unary operator on number in SumFunction");
				}
			}
			
			//If the parameter is a GenericAbstractVariable
			else if(parameter instanceof GenericAbstractVariable){
				GenericAbstractVariable variable = (GenericAbstractVariable) parameter;
				if(variable.getValue() instanceof GenericNumber){
					GenericNumber number = (GenericNumber) variable.getValue();
					result += number.getValue();
				}
				else{
					System.err.println("Unsupported variable value type in SumFunction");
				}
			}			
		}
		return new GenericNumber(result);
	}

}
