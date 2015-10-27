package xx.GenericElements;

/**
 * Enumeration used to define the supported binary operators. 
 * This element is used by the GenericBinaryExpression elements
 * @author Robin ten Buuren
 */

public enum GenericBinaryOperator {
	
	MULTIPLICATION("MULTIPLICATION", "Multiplication", "*"),
	DIVISION("DIVISION", "Division", "/"),
	ADDITION("ADDITION", "Addition", "+"),
	SUBTRACTION("SUBTRACTION", "Subtraction", "-"),
	EQUALS("EQUALS", "Equals", "=="),
	NOTEQUALS("NOTEQUALS", "Not Equals", "!="),
	GT("GT", "Greater Than", ">"),
	GTE("GTE", "Greater Than Equals", ">="),
	LT("LT", "Less Than", "<"),
	LTE("LTE", "Less Than Equals", "<="),
	MOD("MOD", "Modulo", "%"),
	OR("OR", "OR", "||"),
	AND("AND", "AND", "&&");
	
	private String name;
	private String description;
	private String symbol;
	
	/**
	 * Initializes a GenericBinaryOperator using a name, description and symbol
	 * @param name the name of the GenericBinaryOperator
	 * @param description the description of the GenericBinaryOperator
	 * @param symbol the symbol of the GenericBinaryOperator
	 */
	GenericBinaryOperator(String name, String description, String symbol){
		this.name = name;
		this.description = description;
		this.symbol = symbol;
	}
	
	/**
	 * Returns the name of the GenericBinaryOperator
	 * @return the name of the GenericBinaryOperator
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the description of the GenericBinaryOperator
	 * @return the description of the GenericBinaryOperator
	 */
	
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Returns the symbol of the GenericBinaryOperator
	 * @return the symbol of the GenericBinaryOperator
	 */
	
	public String getSymbol(){
		return this.symbol;
	}

}
