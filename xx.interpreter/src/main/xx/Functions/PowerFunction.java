package xx.Functions;

import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericFunction;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;

/**
 * Function that returns the power value of two numbers (first^second)
 * @author Robin ten Buuren
 *
 */

public class PowerFunction extends GenericFunction {

	public PowerFunction() {
		super("Power");
	}

	/**
	 * Returns the power value given a list of parameters (first^second)
	 * @param parameters the list of parameters
	 * @return the power value of the (two) parameters
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
				return new GenericNumber(Math.pow(number1.getValue(), number2.getValue()));
			}
			else{
				System.err.println("Unsupported parameter type found in PowerFunction");
			}
		}
		else{
			System.err.println("Unsupported number of parameters found in PowerFunction");
		}
		return new GenericNull();
	}

}
