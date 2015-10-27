package xx.GenericElements;

import xx.GenericElements.GenericUnaryOperator;

/**
 * Generic unary expression, used for the conversion of unary expression in other languages
 * Has an expression and unary operator
 * @author Robin ten Buuren
 *
 */
public class GenericUnaryExpression implements GenericAbstractElement{
	
	private GenericAbstractElement expression;
	private GenericUnaryOperator unaryOperator;
	
	/**
	 * Initializes a GenericUnaryExpression using an expression and operator
	 * @param expression the expression
	 * @param operator the operator
	 */
	
	public GenericUnaryExpression(GenericAbstractElement expression, GenericUnaryOperator unaryOperator){
		this.expression = expression;
		this.unaryOperator = unaryOperator;
	}
	
	/**
	 * Returns the expression of the GenericUnaryExpression
	 * @return the expression of the GenericUnaryExpression
	 */
	
	public GenericAbstractElement getExpression(){
		return expression;
	}
	
	/**
	 * Returns the unary operator of the GenericUnaryExpression
	 * @return the unary operator of the GenericUnaryExpression
	 */
	
	public GenericUnaryOperator getOperator(){
		return unaryOperator;
	}
	
	/**
	 * Returns the string representation of the GenericUnaryExpression
	 * @return the string representation of the GenericUnaryExpression
	 */

	@Override
	public String toString(){
		return "UnExp: " + unaryOperator.getSymbol() + getExpression().toString();
	}

	/**
	 * Method to clone this GenericUnaryExpression
	 * @return a clone of this GenericUnaryExpression
	 */

	@Override
	public GenericUnaryExpression clone(){
		return new GenericUnaryExpression(expression.clone(), unaryOperator);
	}
	
	/**
	 * Method to calculate the hashCode of this GenericUnaryExpression
	 * @return the hashCode of this GenericUnaryExpression
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result
				+ ((unaryOperator == null) ? 0 : unaryOperator.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericUnaryExpressions
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericUnaryExpression
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericUnaryExpression other = (GenericUnaryExpression) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (unaryOperator != other.unaryOperator)
			return false;
		return true;
	}
}
