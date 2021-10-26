package SaxHLO5ZK_1019;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;

public class ValidationHLO5ZK {

	public static void main(String[] args) {
		final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";   
		final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
		final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
		final String MY_SCHEMA_SOURCE = "szemelyekHLO5ZK.xsd";
		
		try {
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setValidating(true);
			saxParserFactory.setNamespaceAware(true);
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			saxParser.setProperty(JAXP_SCHEMA_SOURCE, MY_SCHEMA_SOURCE);
			
			SaxValidationHandler validationHandler = new SaxValidationHandler();
			
			saxParser.parse(new File("src/SaxHLO5ZK_1019/szemelyekHLO5ZK.xml"), validationHandler);
			
			System.out.println("Succesful Validation!");
		} catch(SAXNotRecognizedException snre) {
			System.out.println("Could not recognize XML Schema!");
		} catch(ParserConfigurationException | IOException pce) {
			System.out.println("Hiba: "+pce.getMessage());
		} catch(SAXException se) {
			System.out.println(se.getMessage());
		}
	}
	
}
