package xx.GenericElements;

import xx.GenericElements.GenericBinaryOperator;

/**
 * Generic binary expression, used for the conversion of binary expression in other languages
 * Has a left expression, right expressions and binary operator
 * @author Robin ten Buuren
 *
 */

public class GenericBinaryExpression implements GenericAbstractElement{
	
	private GenericAbstractElement left;
	private GenericAbstractElement right;
	private GenericBinaryOperator binaryOperator;
	
	/**
	 * Initializes a GenericBinaryExpression using a left expression, right expression and binary operator
	 * @param left the left expression
	 * @param right the right expression
	 * @param binaryOoperator the binary operator
	 */
	
	public GenericBinaryExpression(GenericAbstractElement left, GenericAbstractElement right, GenericBinaryOperator binaryOperator){
		this.left = left;
		this.right = right;
		this.binaryOperator = binaryOperator;
	}
	
	/**
	 * Returns the left expression of the GenericBinaryExpression
	 * @return the left expression of the GenericBinaryExpression
	 */
	
	public GenericAbstractElement getLeft(){
		return left;
	}
	
	/**
	 * Returns the right expression of the GenericBinaryExpression
	 * @return the right expression of the GenericBinaryExpression
	 */
	
	public GenericAbstractElement getRight(){
		return right;
	}
	
	/**
	 * Returns the binary operator of the GenericBinaryExpression
	 * @return the binary operator of the GenericBinaryExpression
	 */
	
	public GenericBinaryOperator getOperator(){
		return binaryOperator;
	}
	
	/**
	 * Returns the string representation of the GenericBinaryExpression
	 * @return the string representation of the GenericBinaryExpression
	 */
	
	@Override
	public String toString(){
		return "BiExp: " + getLeft() + " " + binaryOperator.getSymbol() + " " + getRight();
	}
	
	/**
	 * Method to clone this GenericBinaryExpression
	 * @return a clone of this GenericBinaryExpression
	 */
	
	@Override
	public GenericBinaryExpression clone(){
		return new GenericBinaryExpression(left.clone(), right.clone(), binaryOperator);
	}
	
	/**
	 * Method to calculate the hashCode of this GenericBinaryExpression
	 * @return the hashCode of this GenericBinaryExpression
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((binaryOperator == null) ? 0 : binaryOperator.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericBinaryExpressions
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericBinaryExpression
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericBinaryExpression other = (GenericBinaryExpression) obj;
		if (binaryOperator != other.binaryOperator)
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

}
