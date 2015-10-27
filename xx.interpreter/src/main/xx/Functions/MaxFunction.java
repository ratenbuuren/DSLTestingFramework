package xx.Functions;

import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericFunction;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;

/**
 * Function that returns the maximum value of two numbers
 * @author Robin ten Buuren
 *
 */

public class MaxFunction extends GenericFunction {

	public MaxFunction() {
		super("Max");
	}

	/**
	 * Returns the maximum value given a list of parameters
	 * @param parameters the list of parameters
	 * @return the max value of the (two) parameters
	 */
	@Override
	public GenericAbstractElement getValue(List<GenericAbstractElement> parameters) {
		//Two parameters should be given
		if(parameters.size() == 2){
			GenericAbstractElement parameter0 = parameters.get(0);
			GenericAbstractElement parameter1 = parameters.get(1); 
			
			//If the parameters are GenericNumbers
			if(parameter0 instanceof GenericNumber && parameter1 instanceof GenericNumber){
				GenericNumber number1 = (GenericNumber)parameter0;
				GenericNumber number2 = (GenericNumber)parameter1;
				return new GenericNumber(Math.max(number1.getValue(), number2.getValue()));
			}
			else{
				System.err.println("Unsupported parameter type found in MaxFunction");
			}
		}
		else{
			System.err.println("Unsupported number of parameters found in MaxFunction");
		}
		return new GenericNull();
	}

}
