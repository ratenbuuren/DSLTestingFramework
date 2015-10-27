package xx.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.mozilla.javascript.NativeArray;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericBoolean;
import xx.GenericElements.GenericIfConditional;
import xx.GenericElements.GenericNull;
import xx.GenericElements.GenericNumber;
import xx.GenericElements.GenericString;

/**
 * Class used to call the different options for the Generic Test Generation Framework
 * @author Robin ten Buuren
 *
 */

public class GenericOptions {
	
	/**
	 * Evaluates a String by calling the JavaScript Engine eval() method
	 * @param element the element to be evaluated
	 * @return the evaluated value of the element
	 */
	
	public Object evaluateString(String element){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			return engine.eval(element);
		} catch (ScriptException e) {
			System.err.println("Exception while evaluating expression: " + element);
			e.printStackTrace();
		}
		return "Evaluation Exception";
	}
	
	/**
	 * Evaluates the element by generating the String representation and calling evaluateString()
	 * If the returned element in an instance of NativeArray, a string representation of this array is returned
	 * @param element the element to be evaluated
	 * @return the evaluated value of the element
	 */
	
	public Object evaluateElement(GenericAbstractElement element){
		Object result = evaluateString(generateString(element));
		if(result instanceof NativeArray){
			NativeArray arr = (NativeArray) result;
			String returnString = "[";
			for (Object o : arr.getIds()) {
			    int index = (Integer) o;
			    returnString += (arr.get(index, null)).toString() + ",";
			}
			returnString = RemoveLastComma(returnString) + "]";
			result = returnString;
		}
		return result;
	}
	
	/**
	 * Evaluates an element and parses the result 
	 * @param element the element to be evaluated
	 * @return the parsed result of the evaluated element
	 */
	
	public GenericAbstractElement evaluate(GenericAbstractElement element){
		return transformObject(evaluateString(generateString(element)));
	}
	
	/**
	 * Generates a list of possible cases using list of GenericAbstractElements
	 * Each case contains the preconditions (variables) and postconditions (variables and conditionals)
	 * @param elements the list of parsed elements (GenericAbstractElements)
	 * @return a List of all possible cases to reach branch/condition coverage for the elements
	 */
	
	public List<GenericCase> generateCases(List<GenericAbstractElement> elements){
		List<GenericCase> result = new ArrayList<GenericCase>();
		Map<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> values = generateValues(elements);
		Iterator<Entry<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>>> it = values.entrySet().iterator();
		while(it.hasNext()){
			result.addAll(new GenericCaseGenerator().generateCases(elements, it.next().getValue()));
		}
		return result;
	}
	
	/**
	 * Generates a JBehave story for a list of elements
	 * These elements are given to the GenericCase generator which transforms them to cases
	 * For each case, a scenario is created in the story
	 * @param elements elements for the story
	 * @param language the domain-specific language to select the right generator
	 * @return the String representation of the JBehave story test case
	 */
	
	public String generateStory(List<GenericAbstractElement> elements){
		return new PrecedenceStoryGenerator().generateStory(generateCases(elements));
	}
	
	/**
	 * Generates a Selenium test for a list of elements
	 * These elements are given to the GenericCase generator which transforms them to cases
	 * For each case, a test scenario is created in the test
	 * @param elements elements for the story
	 * @param language the domain-specific language to select the right generator
	 * @return the String representation of the Selenium test case
	 */
	
	public String generateSelenium(List<GenericAbstractElement> elements, String modelName){
		return new PrecedenceSeleniumGenerator().generateTest(generateCases(elements), modelName);
	}
	
	/**
	 * Generates the String representation of the element
	 * @param element the element to be converted
	 * @return a String representation of the element
	 */
	
	public String generateString(GenericAbstractElement element){
		return new GenericStringGenerator().transform(element);
	}
	
	/**
	 * Generates a map of contexts and their map of variables and values given a list of elements
	 * @param elements the base elements
	 * @return a map of elements (contexts) and maps (variables and their possible values) to generate all cases
	 */
	
	public Map<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> generateValues(List<GenericAbstractElement> elements){
		Map<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>> result = new HashMap<GenericAbstractElement, Map<GenericAbstractElement, List<GenericAbstractElement>>>();
		GenericValueGenerator generator = new GenericValueGenerator();
		for(GenericAbstractElement element : elements){
			GenericAbstractElement currentContext = null;
			if(element instanceof GenericIfConditional){
				currentContext = element;
			}
			else if(element instanceof GenericAbstractVariable && currentContext == null){
				GenericAbstractVariable variable = (GenericAbstractVariable) element;
				if(!(variable.getValue() instanceof GenericNull)){
					currentContext = element;
				}
			}
			if(currentContext != null){
				generator.generate(currentContext, element);
			}
			result.putAll(generator.getPossibleValues());
		}
		System.out.println("generateVariableValues Result: " + result);
		return result;
	}
	
	/**
	 * Transforms an Java Object to a GenericAbstractElement[], GenericNull, GenericBoolean, GenericNumber or GenericString
	 * @param object the object to be transformed
	 * @return the Generic representation of the object
	 */
	
	public GenericAbstractElement transformObject(Object object){
		if(object == null){
			return new GenericNull();
		}
		if(object instanceof NativeArray){
			NativeArray arr = (NativeArray) object;
			GenericAbstractElement[] array = new GenericAbstractElement[(int) arr.getLength()];
			for (Object o : arr.getIds()) {
			    int index = (Integer) o;
			    array[index] = transformObject(arr.get(index, null));
			}
		}
		String stringResult = object.toString();
		if("true".equals(stringResult) || "false".equals(stringResult)){
			return new GenericBoolean(Boolean.parseBoolean(stringResult));
		}
		else if(isDouble(object)){
			return new GenericNumber(Double.parseDouble(object.toString()));
		}
		else return new GenericString(stringResult);
	}
	
	/**
	 * Checks whether the object can be transformed to a double, else returns false
	 * @param object the object to be transformed
	 * @return whether the object can be transformed
	 */
	private boolean isDouble(Object object){
		try{
			Double.parseDouble(object.toString());
			return true;
		}
		catch (NumberFormatException nfe) {
            return false;
		}
	}
	
	/**
	 * Removes the last character of a String if it is a comma
	 * @param string the original String
	 * @return the String without the comma
	 */
	public String RemoveLastComma(String string) {
	    if (null != string && string.length() > 0 && string.charAt(string.length()-1)==',') {
	      return string.substring(0, string.length()-1);
	    }
	    return string;
	}

}
