package org.xtext.evaluation

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.xtext.operator.precedence.AbstractElement
import org.xtext.operator.precedence.Variable
import xx.GenericElements.GenericAbstractElement
import xx.Generators.GenericOptions
import org.xtext.operator.precedence.MockDef
import org.xtext.operator.precedence.MockEntry
import java.io.IOException
import org.eclipse.emf.common.util.URI
import com.google.inject.Injector
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import com.google.inject.Guice
import org.xtext.operator.PrecedenceRuntimeModule
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList

class PrecedenceOptions implements IGenerator
{
	private var options = new GenericOptions();
	private var transformer = new PrecedenceTransformer();
	private var List<GenericAbstractElement> transformedElements = new ArrayList<GenericAbstractElement>();
	
	/**
	 * Generate method called after the model file is changed
	 * @param resourceSet the content of the file
	 * @param fileSystemAccess used to write a file
	 */
	 
	override doGenerate(Resource resource, IFileSystemAccess fileSystemAccess)
	{
		//generateXMI();
		//printXMI();
		//Initialize variables
		options = new GenericOptions();
		transformer = new PrecedenceTransformer();
		transformer.instantiateDefaultFunctions();
		transformedElements.clear();
		//Transform the variables
		for(variable : resource.allContents.toIterable.filter(typeof(Variable))){
			transformer.transform(variable);
		}
		//Transform the mock definitions
		for(mockDef : resource.allContents.toIterable.filter(typeof(MockDef))){
			transformer.transform(mockDef);
		}
		//Transform the mock entries
		for(mockEntry : resource.allContents.toIterable.filter(typeof(MockEntry))){
			transformer.transform(mockEntry);
		}
		//Transform the AbstractElements
		for(element : resource.allContents.toIterable.filter(typeof(AbstractElement))){
			transformedElements.add(transformer.transform(element));
			//generateString(element);
			//evaluate(element);
		}
		//System.out.println("TransformedElements:" + transformedElements);
		generateStory("Test", fileSystemAccess);
		generateSelenium("Precedence", fileSystemAccess);
	}
	
	def generateXMI(){
		var Injector injector = Guice.createInjector(new PrecedenceRuntimeModule);
		
		var XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet);
		
		var URI uri = URI.createURI("/Operators/src/test.precedence");
		var Resource xtextResource = resourceSet.getResource(uri, true);
		
		EcoreUtil.resolveAll(xtextResource);
		
		var Resource xmiResource = resourceSet.createResource(URI.createURI("/Operators/src/test.xmi"));
		xmiResource.getContents().add(xtextResource.getContents().get(0));
		try {
			xmiResource.save(null);
			System.out.println("Generated XMI file");
		} catch (IOException e) {
			e.printStackTrace
		}
	}
	
	def printXMI(){
		var Injector injector = Guice.createInjector(new PrecedenceRuntimeModule);
		
		var XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet);
		
		var URI uri = URI.createURI("/Operators/src/ztest.xmi");
		var Resource xtextResource = resourceSet.getResource(uri, true);
		
		EcoreUtil.resolveAll(xtextResource);
		var EList<EObject> list =  xtextResource.contents;
		for(EObject object : list){
			System.out.println("Resource object class: " + object.class);
			System.out.println("Resource object: " + object);
		}
		
	}
	
	/**
	 * Generates the String representation for a AbstractElement element by transforming it and calling the generic options method
	 * @param element the element to be transformed
	 */
	
	def generateString(AbstractElement element){
		System.out.println("Precedence base element: " + element);
		var String transformedExpression = options.generateString(transformer.transform(element))
		System.out.println("Precedence transformed expression: " + transformedExpression + "\n");
	}
	
	/**
	 * Evaluates an AbstractElement element by transforming it and calling the generic options method
	 * @param element the element to be transformed
	 */
	
	def evaluate(AbstractElement element){
		System.out.println("Precedence element: " + element);
		System.out.println("Precedence result: " + options.evaluate(transformer.transform(element)) + "\n");
	}
	
	/**
	 * Generates the cases using the transformed elements
	 */
	
	def void generateCases(){
		System.out.println("Precedence Cases Generation started");
		options.generateCases(transformedElements);
	}
	
	/**
	 * Generates the JBehave story by calling the generateStory method of the generic options
	 * @param fileName the resulting name of the JBehave file
	 * @param fileSystemAccess used to generate a file
	 */
	
	def void generateStory(String fileName, IFileSystemAccess fileSystemAccess){
		System.out.println("Precedence Stories Generation started");
		fileSystemAccess.generateFile("/test/" + fileName + ".story", options.generateStory(transformedElements, "Precedence"))
	}
	
	/**
	 * Generates the Selenium test case by calling the generateSelenium method of the generic options
	 * @param fileName the resulting name of the Selenium file
	 * @param fileSystemAccess used to generate a file
	 */
	 
	def void generateSelenium(String fileName, IFileSystemAccess fileSystemAccess){
		System.out.println("Precedence Selenium Generation started");
		fileSystemAccess.generateFile("/test/Selenium" + fileName + ".java", options.generateSelenium(transformedElements, "Precedence", fileName))
	}
}	