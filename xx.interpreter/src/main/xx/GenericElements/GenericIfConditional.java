package xx.GenericElements;

/**
 * Generic If-Else construct , used for the conversion of If-Else constructs in other languages
 * Has a name, condition, true clause and false clause
 * @author Robin ten Buuren
 *
 */

public class GenericIfConditional implements GenericConditional{
	
	private String name;
	private GenericAbstractElement condition;
	private GenericAbstractElement ifClause;
	private GenericAbstractElement elseClause;
	
	/**
	 * Initializes a GenericIfContional using a name, condition, if-clause and else-clause
	 * @param name the name
	 * @param condition the condition
	 * @param trueClause the true clause
	 * @param falseClause the false clause
	 */
	
	public GenericIfConditional(String name, GenericAbstractElement condition, GenericAbstractElement ifClause, GenericAbstractElement elseClause){
		this.name = name;
		this.condition = condition;
		this.ifClause = ifClause;
		this.elseClause = elseClause;
	}
	
	/**
	 * Initializes a GenericIfContional using a name, condition and if-clause. The else-clause is set to a GenericNull
	 * @param name the name
	 * @param condition the condition
	 * @param trueChoice the true clause
	 */
	
	public GenericIfConditional(String name, GenericAbstractElement condition, GenericAbstractElement ifClause){
		this(name, condition, ifClause, new GenericNull());
	}
	
	/**
	 * Returns the name of the GenericIfConditional
	 * @return
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the condition of the GenericIfConditional
	 * @return the condition of the GenericIfConditional
	 */
	
	public GenericAbstractElement getCondition(){
		return this.condition;
	}
	
	/**
	 * Returns the if-clause of the GenericIfConditional
	 * @return the if-clause of the GenericIfConditional
	 */
	
	public GenericAbstractElement getIfClause(){
		return ifClause;
	}
	
	/**
	 * Returns the false-clause of the GenericIfConditional
	 * @return the false-clause of the GenericIfConditional
	 */

	public GenericAbstractElement getElseClause(){
		return elseClause;
	}
	
	/**
	 * Returns whether the GenericIfConditional contains a else-clause
	 * @return whether the GenericIfConditional contains a else-clause
	 */
	
	public boolean containsElse(){
		return !(elseClause instanceof GenericNull);
	}
	
	/**
	 * Returns the string representation of the GenericIfConditional
	 * @return the string representation of the GenericIfConditional
	 */

	@Override
	public String toString(){
		return "IfCondition " + name + ": " + condition.toString();
	}
	
	/**
	 * Method to clone this GenericIfConditional
	 * @return a clone of this GenericIfConditional
	 */

	@Override
	public GenericIfConditional clone(){
		return new GenericIfConditional(name, condition.clone(), ifClause.clone(), elseClause.clone());
	}
	
	/**
	 * Method to calculate the hashCode of this GenericIfConditional
	 * @return the hashCode of this GenericIfConditional
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result
				+ ((elseClause == null) ? 0 : elseClause.hashCode());
		result = prime * result
				+ ((ifClause == null) ? 0 : ifClause.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * Method to compare two GenericIfConditionals
	 * @param obj the object to be compared
	 * @return whether the parameter object is equal to this GenericIfConditional
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericIfConditional other = (GenericIfConditional) obj;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (elseClause == null) {
			if (other.elseClause != null)
				return false;
		} else if (!elseClause.equals(other.elseClause))
			return false;
		if (ifClause == null) {
			if (other.ifClause != null)
				return false;
		} else if (!ifClause.equals(other.ifClause))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
