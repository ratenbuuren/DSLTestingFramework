package xx.Generators;

import java.util.List;

import xx.GenericElements.GenericAbstractElement;
import xx.GenericElements.GenericAbstractVariable;
import xx.GenericElements.GenericConditional;
import xx.GenericElements.GenericIfConditional;

/**
 * Class used to generate the String representation for a Selenium test given a list of generic cases
 * @author Robin ten Buuren
 *
 */

public class PrecedenceSeleniumGenerator {
	
	//Used to generate String representation and evaluate elements
	private GenericOptions options = new GenericOptions();
	
	/**
	 * Generates the String representation for a Selenium test given a list of cases
	 * This is done by traversing the list of cases and creating a scenario by transforming
	 * the new preconditions to sendKeys statements and postconditions to assert statements for each case 
	 * @param cases the list of generic cases
	 * @return the String representation of the JBehave story
	 */
	
	public String generateTest(List<GenericCase> cases, String modelName){
		String result = generatePrefix(modelName);
		int caseCounter = 1;
		//Traverse the cases
		for(GenericCase currentCase : cases){
			String caseResult = "@Test\n" + "public void testCase" + caseCounter + "() throws Exception {\n";
			//Add preconditions
			for(GenericAbstractElement precondition : currentCase.getPreconditions()){
				//Identify type of GenericAbstractElement
				if(precondition instanceof GenericAbstractVariable){
					GenericAbstractVariable variable = (GenericAbstractVariable)precondition;
					caseResult += "\tdriver.findElement(By.xpath(\"//*[@example-id[=\'" + variable.getName() 
							+ "\']/input[@example-id='value']\")).sendKeys(\"" + options.evaluateElement(variable.getValue()) + "\");\n";
				}
				else{
					caseResult += "Encountered unsupported precondition type";
				}
			}
			caseResult += "\n";
			//Add postconditions
			for(GenericAbstractElement postcondition : currentCase.getPostconditions()){
				//Identify type of GenericAbstractElement
				if(postcondition instanceof GenericAbstractVariable){
					GenericAbstractVariable variable = (GenericAbstractVariable)postcondition;
					caseResult += "\tAssert.assertEquals(\""+ options.evaluateElement(variable.getValue()) + "\", driver.findElement(By.xpath(\"//*[@example-id[=\'" 
					+ variable.getName() + "\']//*[@example-id='value']\")));\n";
				}
				else if(postcondition instanceof GenericConditional){
					if(postcondition instanceof GenericIfConditional){
						GenericIfConditional postIfConditional = (GenericIfConditional)postcondition;
						caseResult += "\tAssert.assertEquals(\""+ options.evaluateElement(postIfConditional) + "\", driver.findElement(By.xpath(\"//*[@example-id[=\'" 
						+ postIfConditional.getName() + "\']//*[@example-id='value']\")));\n";
					}
					else{
						caseResult += "Encountered unsupported GenericConditional while adding postconditions";
					}
				}
				else{
					caseResult += "Encountered unsupported postcondition type";
				}
			}
			caseResult += "}\n\n";
			//Update case counter
			caseCounter++;
			//Add case result to final result
			result += caseResult;
		}
		return result + "}";
	}
	
	/**
	 * Prefix used to create a test that validates to the Selenium syntax 
	 * @param modelName the name of the model to be tested
	 * @return the prefix for the test
	 */
	
	public String generatePrefix(String modelName){
		return "package test;" + 
				"\n" + 
				"\n" + 
				"import java.util.concurrent.TimeUnit;" + "\n" +
				"\n" + 
				"import org.junit.*;" + "\n" +
				"import org.openqa.selenium.*;" + "\n" +
				"import org.openqa.selenium.firefox.FirefoxDriver;" + "\n" +
				"\n" +
				"public class Selenium" + modelName +  " {" + "\n" +
				"\n" +
				"private WebDriver driver;" + "\n" + 
				"\n" +
				"@Before" + "\n" +
				"public void setUp() throws Exception {" + "\n" + 
				"\tdriver = new FirefoxDriver();" + "\n" + 
				"\tdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);" + "\n" +
				"\tdriver.get(\"http://example.com/\");" + "\n" + 
				"\tdriver.findElement(By.id(\"inputUsername\")).sendKeys(\"username\");" + "\n" + 
				"\tdriver.findElement(By.id(\"inputPassword\")).sendKeys(\"password\");" + "\n" + 
				"\tdriver.findElement(By.id(\"loginButton\")).click();" + "\n" + 
				"\t}" + "\n" +
				"\n";
	}
}
