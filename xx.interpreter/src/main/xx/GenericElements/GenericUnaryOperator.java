package xx.GenericElements;

/**
 * Enumeration used to define the supported unary operators. 
 * This element is used by the GenericUnaryExpression elements
 * @author Robin ten Buuren
 */

public enum GenericUnaryOperator {
	
	NEGATE("NEGATE", "Negate", "!"),
	NEGATIVE("NEG", "Negative", "-"),
	POSITIVE("POS", "Positive", "+");
	
	private String name;
	private String description;
	private String symbol;
	
	/**
	 * Initializes a GenericUnaryOperator using a name, description and symbol
	 * @param name the name of the GenericUnaryOperator
	 * @param description the description of the GenericUnaryOperator
	 * @param symbol the symbol of the GenericUnaryOperator
	 */
	GenericUnaryOperator(String name, String description, String symbol){
		this.name = name;
		this.description = description;
		this.symbol = symbol;
	}
	
	/**
	 * Returns the name of the GenericUnaryOperator
	 * @return the name of the GenericUnaryOperator
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the description of the GenericUnaryOperator
	 * @return the description of the GenericUnaryOperator
	 */
	
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Returns the symbol of the GenericUnaryOperator
	 * @return the symbol of the GenericUnaryOperator
	 */
	
	public String getSymbol(){
		return this.symbol;
	}

}
